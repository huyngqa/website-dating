package com.codegym.dating.repository;

import com.codegym.dating.dto.IPostDto;
import com.codegym.dating.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface IPostRepository extends JpaRepository<Post, Integer> {
    @Modifying
    @Query(value = " INSERT INTO post ( `content` , `media` , `time` , `id_user`)" +
            " VALUES (:content, :media, :time, :id_user) ",
            nativeQuery = true)
    void savePost(@Param("content") String content, @Param("media") String media, @Param("time") LocalDateTime time,
                  @Param("id_user") Integer id);

    @Query(value = "SELECT \n" +
            "    p.content AS content,\n" +
            "    p.id_user AS idUser,\n" +
            "    p.media AS media,\n" +
            "    p.time AS time,\n" +
            "    p.id_post AS idPost,\n" +
            "    u.avatar AS avatar,\n" +
            "    u.name AS Username\n" +
            "FROM\n" +
            "    post AS p\n" +
            "        JOIN\n" +
            "    user AS u ON p.id_user = u.id_user\n" +
            "WHERE\n" +
            "    p.id_user = ?1 " +
            "ORDER BY time DESC", nativeQuery = true)
    List<IPostDto> getUserPostList(int id);

    @Query(value = "select p.content as content,\n" +
            "        p.id_user as idUser,\n" +
            "        p.media as media,\n" +
            "         p.time as time,\n" +
            "         p.id_post as idPost,\n" +
            "         u.avatar as avatar,\n" +
            "         u.name as Username\n" +
            "from post as p\n" +
            " join user as u on p.id_user = u.id_user\n" +
            "where p.id_user = ?1 or p.id_user in\n" +
            " ( \n" +
            " select f.id_user2\n" +
            " from user as u\n" +
            "  left join friend_list as f  on u.id_user=f.id_user1\n" +
            "   where f.status = 6 and (f.id_user1 = ?1 or f.id_user2= ?1) \n" +
            "   group by u.id_user)\n" +
            "   order by p.time DESC", nativeQuery = true)
    List<IPostDto> getPostList(int id);

        @Query(value = "select p.content as content,\n" +
                "        p.id_user as idUser,\n" +
                "        p.media as media,\n" +
                "         p.time as time,\n" +
                "         p.id_post as idPost,\n" +
                "         u.avatar as avatar,\n" +
                "         u.name as Username\n" +
                "from post as p\n" +
                " join user as u on p.id_user = u.id_user\n" +
                "where p.id_post= ?1 ",nativeQuery = true)
        IPostDto  findPostById(int id);

        @Modifying
        @Query(value = "update post as p \n" +
                "            set p.content= ?1,p.media= ?2\n" +
                "            where p.id_user= ?3 and p.id_post= ?4", nativeQuery = true)
        void updatePost(String content, String media, int idUserUp, int idPostUp);


    }
