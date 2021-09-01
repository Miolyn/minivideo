package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.LoginRecord;
import cn.tju.minivideo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginRecordMapper extends BaseMapper<LoginRecord> {
    int deleteByPrimaryKey(Integer loginRecordId);

    int insert(LoginRecord record);

    int insertSelective(LoginRecord record);

    LoginRecord selectByPrimaryKey(Integer loginRecordId);

    int updateByPrimaryKeySelective(LoginRecord record);

    int updateByPrimaryKey(LoginRecord record);
}