package com.codegym.dating.repository;

import com.codegym.dating.dto.ICommentDto;
import com.codegym.dating.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ICommentRepository extends JpaRepository<Comment, Integer> {

    @Modifying
    @Query(value = "insert into comment(`content`,`id_post`,`id_user`)\n" +
            "values (:content,:id_post,:id_user)", nativeQuery = true)
    void addComment(@Param("content") String content, @Param("id_post") Integer post, @Param("id_user") Integer idUser);

    @Query(value ="select   c.content as \tcontent , \n" +
            "\t\t  c.id_user as idUser  , \n" +
            "          c.id_post as idPost , \n" +
            "          u.name as name, \n" +
            "          u.avatar as avatar \n" +
            " from comment as c\n" +
            "            join post p \n" +
            "            on c.id_post = p.id_post \n" +
            "            join user u \n" +
            "            on c.id_user = u.id_user\n" +
            "\t\twhere p.id_post = :id_post " , nativeQuery = true)
    List<ICommentDto> displayComment(@Param("id_post") Integer id_post);


}