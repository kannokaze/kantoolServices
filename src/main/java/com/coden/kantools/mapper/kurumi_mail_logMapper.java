package com.coden.kantools.mapper;

import com.coden.kantools.model.kurumi_mail_log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface kurumi_mail_logMapper {

    @Insert("Insert into kurumi_mail_log(email,system,logoPath,username,title,content,mark,sender,mailType,createTime) values(#{email},#{system},#{logoPath},#{username},#{title},#{content},#{mark},#{system},#{mailType},now())")
    Boolean insert(kurumi_mail_log record);

    //    直接使用万能的HashMap传参，少了数据转化的步骤，也减少编写大量POJO类的烦恼，但数据量多的会影响性能
    @Insert("Insert into kurumi_mail_log(email,system,logoPath,username,title,content,mark,sender,mailType,createTime) values(#{record.email},#{record.system},#{record.logoPath},#{record.username},#{record.title},#{record.content},#{record.mark},#{record.system},#{record.mailType},now())")
    Boolean insert1(@Param("record") HashMap record);

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

    //    直接使用万能的HashMap作为结果集，少了数据转化的步骤，也减少编写大量POJO类的烦恼，但数据量多的会影响性能
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
    List<HashMap> selectByAny1(kurumi_mail_log record);


}