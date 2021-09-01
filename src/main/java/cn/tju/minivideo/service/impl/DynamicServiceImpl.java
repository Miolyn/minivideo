package cn.tju.minivideo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.DynamicMapper;
import cn.tju.minivideo.entity.Dynamic;
import cn.tju.minivideo.service.DynamicService;
@Service
public class DynamicServiceImpl implements DynamicService{

    @Resource
    private DynamicMapper dynamicMapper;

    @Override
    public int deleteByPrimaryKey(Integer dynamicId) {
        return dynamicMapper.deleteByPrimaryKey(dynamicId);
    }

    @Override
    public int insert(Dynamic record) {
        return dynamicMapper.insert(record);
    }

    @Override
    public int insertSelective(Dynamic record) {
        return dynamicMapper.insertSelective(record);
    }

    @Override
    public Dynamic selectByPrimaryKey(Integer dynamicId) {
        return dynamicMapper.selectByPrimaryKey(dynamicId);
    }

    @Override
    public int updateByPrimaryKeySelective(Dynamic record) {
        return dynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Dynamic record) {
        return dynamicMapper.updateByPrimaryKey(record);
    }

}
