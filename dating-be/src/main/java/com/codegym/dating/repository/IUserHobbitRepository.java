package com.codegym.dating.repository;

import com.codegym.dating.dto.HobbitDto;
import com.codegym.dating.model.UserHobbit;
import com.codegym.dating.model.composite.UserHobbitKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserHobbitRepository extends JpaRepository<UserHobbit, UserHobbitKey> {
    @Query(value = "SELECT \n" +
            "    h.hobbit_name AS hobbitName\n" +
            "FROM\n" +
            "    hobbit h\n" +
            "        JOIN\n" +
            "    user_has_hobbit ush ON ush.id_hobbit = h.id_hobbit\n" +
            "WHERE\n" +
            "    ush.id_user = ?1",nativeQuery = true)
    List<HobbitDto> findAllByIdUser(Integer id);

    @Transactional
    @Modifying
    @Query(value = "insert into user_has_hobbit(id_user, id_hobbit) " +
            "value (:#{#userHobbit.user.idUser}, :#{#userHobbit.hobbit.idHobbit})", nativeQuery = true)
    void saveUserHobbit(UserHobbit userHobbit);


}
