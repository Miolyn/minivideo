package cn.tju.minivideo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.LoginRecordMapper;
import cn.tju.minivideo.entity.LoginRecord;
import cn.tju.minivideo.service.LoginRecordService;
@Service
public class LoginRecordServiceImpl implements LoginRecordService{

    @Resource
    private LoginRecordMapper loginRecordMapper;

    @Override
    public int deleteByPrimaryKey(Integer loginRecordId) {
        return loginRecordMapper.deleteByPrimaryKey(loginRecordId);
    }

    @Override
    public int insert(LoginRecord record) {
        return loginRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(LoginRecord record) {
        return loginRecordMapper.insertSelective(record);
    }

    @Override
    public LoginRecord selectByPrimaryKey(Integer loginRecordId) {
        return loginRecordMapper.selectByPrimaryKey(loginRecordId);
    }

    @Override
    public int updateByPrimaryKeySelective(LoginRecord record) {
        return loginRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(LoginRecord record) {
        return loginRecordMapper.updateByPrimaryKey(record);
    }

}
