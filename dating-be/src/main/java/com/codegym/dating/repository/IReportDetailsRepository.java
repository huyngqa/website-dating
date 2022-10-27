package com.codegym.dating.repository;

import com.codegym.dating.dto.IReportDetailsDto;
import com.codegym.dating.dto.ReportDetailDto;
import com.codegym.dating.dto.UserReportDto;
import com.codegym.dating.model.ReportDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface IReportDetailsRepository extends JpaRepository<ReportDetails, Integer> {
    @Transactional
    @Query(value = "select new com.codegym.dating.dto.ReportDetailDto(u.idUser,u.name, rd.timeReport, r.nameReport, rd.reporter.name) " +
            "from ReportDetails rd left join Report r on r.idReport = rd.report.idReport join Post p on p.idPost = rd.post.idPost " +
            "left join User u on u.idUser = p.user.idUser  " +
            "where u.idUser = :id and rd.status = 9")
    List<ReportDetailDto> findByIdReportDetail(@Param("id") int id);

    @Query(value = "SELECT " +
            "      new com.codegym.dating.dto.UserReportDto(u.idUser, " +
            "      count(rd)) " +
            "   FROM " +
            "      ReportDetails rd " +
            "   JOIN Post p ON " +
            "      rd.post.idPost = p.idPost " +
            "   JOIN User u ON " +
            "      u.idUser = p.user.idUser " +
            "   WHERE " +
            "      rd.status = 9 AND  u.idUser IN ?1 GROUP BY u.idUser")
    List<UserReportDto> findByUserList(List<Integer> userIds);

    @Transactional
    @Query(value = "SELECT " +
            "report_details.id as id, " +
            "report_details.id_report as report, " +
            "report_details.id_post as post," +
            "report_details.id_reporter as reporter," +
            "reporter.`name` as userReport, "+
            "report_details.time_report as timeReport, " +
            "writer.`name` as userPost , " +
            "report_details.status as status, " +
            "report.name_report as reportContent, "+
            "post.content as postContent " +
            "FROM report_details " +
            "Join " +
            "report on report.id_report = report_details.id_report "+
            "JOIN " +
            "`user` reporter on reporter.id_user = report_details.id_reporter " +
            "JOIN " +
            "post ON post.id_post = report_details.id_post " +
            "JOIN " +
            "`user` writer on writer.id_user = post.id_user " +
            "WHERE " +
            "report_details.status = 8 and (post.content like %:keyWord% or writer.`name` like %:keyWord%)", nativeQuery = true,
            countQuery = "select count(*) from (SELECT " +
                    "report_details.id as idReportDetails, " +
                    "report_details.id_report as idReport, " +
                    "report_details.id_post as idPost, " +
                    "reporter.`name` as reporter, " +
                    "report_details.time_report as timeReport, " +
                    "writer.`name` as userPost, " +
                    "report_details.status as status, " +
                    "post.content as postContent " +
                    "FROM report_details " +
                    "JOIN " +
                    "`user` reporter on reporter.id_user = report_details.id_reporter " +
                    "JOIN " +
                    "post ON post.id_post = report_details.id_post " +
                    "JOIN " +
                    "`user` writer on writer.id_user = post.id_user " +
                    "WHERE " +
                    "report_details.status = 8 and (post.content like %:keyWord% or writer.`name` like %:keyWord%)) report_details")
    Page<IReportDetailsDto> findByAllReportUser(@Param("keyWord") String keyWord, Pageable pageable);

    @Transactional
    @Query(value = "select report_details.id_report,report_details.id_post, report_details.id_reporter, report_details.time_report,report_details.status, report_details.id" +
            " from  report_details where (report_details.id = :id and report_details.status = 8) ", nativeQuery = true)
    ReportDetails findReportDetailsByID(@Param("id") Integer id);


    @Modifying
    @Transactional
    @Query(value = "insert into report_details(id, id_report, id_post, id_reporter, " +
            "time_report, status)" +
            "values (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    void createReportDetails(@Param("id") Integer id, @Param("report")Integer report, @Param("post") Integer post, @Param("reporter") Integer reporter, @Param("timeReport") LocalDateTime timeReport, @Param("status") Integer status);


}
