package cn.tju.minivideo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.LabelMapper;
import cn.tju.minivideo.entity.Label;
import cn.tju.minivideo.service.LabelService;
@Service
public class LabelServiceImpl implements LabelService{

    @Resource
    private LabelMapper labelMapper;

    @Override
    public int deleteByPrimaryKey(Integer labelId) {
        return labelMapper.deleteByPrimaryKey(labelId);
    }

    @Override
    public int insert(Label record) {
        return labelMapper.insert(record);
    }

    @Override
    public int insertSelective(Label record) {
        return labelMapper.insertSelective(record);
    }

    @Override
    public Label selectByPrimaryKey(Integer labelId) {
        return labelMapper.selectByPrimaryKey(labelId);
    }

    @Override
    public int updateByPrimaryKeySelective(Label record) {
        return labelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Label record) {
        return labelMapper.updateByPrimaryKey(record);
    }

}
