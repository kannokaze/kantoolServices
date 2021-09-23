package com.coden.kantools.mapper;

import com.coden.kantools.model.kurumi_mail_log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface kurumi_mail_logMapper {

    @Insert("Insert into kurumi_mail_log(email,system,logoPath,username,title,content,mark,sender,mailType,createTime) values(#{email},#{system},#{logoPath},#{username},#{title},#{content},#{mark},#{system},#{mailType},now())")
    Boolean insert(kurumi_mail_log record);

    @Insert("Insert into kurumi_mail_log(email,system,logoPath,username,title,content,mark,sender,mailType,createTime) values(#{email},#{system},#{logoPath},#{username},#{title},#{content},#{mark},#{system},#{mailType},now())")
    Boolean insertList(kurumi_mail_log record);

    @Select({"<script>SELECT * FROM kurumi_mail_log " +
            "<where>" +
            "    <if test=\"email!=null and email!=''\">" +
            "       AND email = #{email}" +
            "    </if>" +
            "    <if test=\"system!=null and system!=''\">" +
            "       AND system = #{system}" +
            "    </if>" +
            "    <if test=\"username!=null and username!=''\">" +
            "       AND username = #{username}" +
            "    </if>" +
            "    <if test=\"title!=null and title!=''\">" +
            "       AND title LIKE CONCAT('%',#{title},'%')" +
            "    </if>" +
            "    <if test=\"content!=null and content!=''\">" +
            "       AND content LIKE CONCAT('%',#{content},'%')" +
            "    </if>" +
            "</where>" +
            "</script>"})
    List<kurumi_mail_log> selectByAny(kurumi_mail_log record);


}