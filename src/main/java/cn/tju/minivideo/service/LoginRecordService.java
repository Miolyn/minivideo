package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.LoginRecord;
public interface LoginRecordService{


    int deleteByPrimaryKey(Integer loginRecordId);

    int insert(LoginRecord record);

    int insertSelective(LoginRecord record);

    LoginRecord selectByPrimaryKey(Integer loginRecordId);

    int updateByPrimaryKeySelective(LoginRecord record);

    int updateByPrimaryKey(LoginRecord record);

}
