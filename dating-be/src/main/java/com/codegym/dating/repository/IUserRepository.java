package com.codegym.dating.repository;

import com.codegym.dating.dto.UserDto;
import com.codegym.dating.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT \n" +
            "    id_user AS idUser,\n" +
            "    address,\n" +
            "    avatar,\n" +
            "    coin,\n" +
            "    date_of_birth AS dateOfBirth,\n" +
            "    gender,\n" +
            "    job,\n" +
            "    join_day AS joinDay,\n" +
            "    married,\n" +
            "    name,\n" +
            "    id_status_active AS idStatusActive,\n" +
            "    id_type_user AS idTypeUser\n" +
            "FROM\n" +
            "    user\n" +
            "WHERE\n" +
            "    id_user = ?1\n", nativeQuery = true)
        Optional<UserDto> findByIdDto(Integer id);

    @Query(value = "select * from user u\n" +
            "join account as a  \n" +
            "on u.id_user = a.id_user\n" +
            "join type_user as tu \n" +
            "on u.id_type_user = tu.id_type_user\n" +
            "where u.id_user = :id ", nativeQuery = true)
    User findByIdNativeQuery(@Param("id") Integer id);



    @Modifying
    @Query(value = "update user \n" +
            "set coin = :coin \n" +
            "where id_user = :id_user ", nativeQuery = true)
    void updateCoin(@Param("coin") Integer coin,@Param("id_user") Integer idUser);


    @Modifying
    @Query(value = "update user \n" +
            "set id_type_user = :id_type_user \n" +
            "where id_user = :id_user ", nativeQuery = true)
    void updateTypeUser(@Param("id_type_user") Integer idTypeUser,@Param("id_user") Integer idUser);

    @Query(nativeQuery = true,
            value = "select c.id_user as idUser,c.name , c.job , c.gender , h.hobbit_name as hobbitName ,c.date_of_birth as dateOfBirth, c.coin ,c.address, c.avatar " +
                    "from user as c " +
                    "join user_has_hobbit as uhh " +
                    "on c.id_user = uhh.id_user " +
                    "join hobbit as h " +
                    "on uhh.id_hobbit = h.id_hobbit " +
                    "where c.name like ?1 " +
                    "and year(c.date_of_birth) >= ?2 " +
                    "and c.address like ?3 " +
                    "and c.job like ?4 " +
                    "and (c.gender = ?5 or c.gender = ?6) " +
                    "and h.hobbit_name like ?7 group by c.id_user ",
            countQuery = "select c.id_user as idUser,c.name , c.job , c.gender , h.hobbit_name as hobbitName ,c.date_of_birth as dateOfBirth, c.coin ,c.address, c.avatar " +
                    "from user as c " +
                    "join user_has_hobbit as uhh " +
                    "on c.id_user = uhh.id_user " +
                    "join hobbit as h " +
                    "on uhh.id_hobbit = h.id_hobbit " +
                    "where c.name like ?1 " +
                    "and year(c.date_of_birth) >= ?2 " +
                    "and c.address like ?3 " +
                    "and c.job like ?4 " +
                    "and (c.gender = ?5 or c.gender = ?6) " +
                    "and h.hobbit_name like ?7 group by c.id_user ")
    Page<UserDto> findAllUserAndSearch(String name,
                                       String dateOfBirth,
                                       String address,
                                       String job,
                                       int gender0,
                                       int gender1,
                                       String hobbitName,
                                       Pageable pageable);

    @Query(value = "SELECT user.id_user as idUser, user.`name`,user.coin,user.gender,user. address,user.job,user .avatar FROM user  where  user.`name` like :name ",
            countQuery = "select count(*) from (SELECT user.id_user as idUser, user.`name`,user.coin,user.gender,user. address,user.job,user .avatar FROM user  where  user.`name` like :name )as pageCount",
            nativeQuery = true)
    Page<UserDto> getAllSearchPage(Pageable pageable, @Param("name") String name);

    @Query(value = "SELECT user.`name`,user.coin,user.gender,user. address,user.job,user .avatar FROM user where  user.`name` like :name"
         , nativeQuery = true)
    List<UserDto> getAllSearch(@Param("name") String name);



    @Modifying
    @Query(value = "update user \n" +
            "set avatar = :avatar \n" +
            "where id_user = :id_user ;", nativeQuery = true)
    void updateAvatar(@Param("avatar") String avatar, @Param("id_user") Integer idUser);


    @Modifying
    @Query(value = "update user \n" +
            "set user.id_status_active = :status \n" +
            "where user.id_user = :id_user", nativeQuery = true)
    void updateStatusActive(@Param("status") Integer status, @Param("id_user") Integer idUser);

    Page<User> findByNameContaining(String name, Pageable pageable);

    @Query(value="select u from User u join TypeUser tu on tu.idTypeUser = u.typeUser.idTypeUser where u.name LIKE lower(concat('%', ?1,'%')) AND tu.typeUserName = ?2 ORDER BY u.idUser ASC")
    Page<User> findByTypeUser(String name, String typeUser, Pageable pageable);

    User findByAccount_IdAccount(Integer idAccount);
}
