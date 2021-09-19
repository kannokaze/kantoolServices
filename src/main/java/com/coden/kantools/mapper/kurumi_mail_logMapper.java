package com.coden.kantools.mapper;

import com.coden.kantools.model.kurumi_mail_log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface kurumi_mail_logMapper {

    @Insert("Insert into ")
    List<kurumi_mail_log> insertList(List<kurumi_mail_log> record);

    @Select("SELECT * FROM kurumi_mail_log")
    List<kurumi_mail_log> selectByAny(kurumi_mail_log record);


}