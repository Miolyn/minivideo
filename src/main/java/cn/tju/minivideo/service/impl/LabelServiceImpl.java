package cn.tju.minivideo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.LabelMapper;
import cn.tju.minivideo.entity.Label;
import cn.tju.minivideo.service.LabelService;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

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


    @Override
    public boolean isExistByLabelNameAndLabelType(String labelName, Integer labelType) {
        return labelMapper.findByLabelNameAndLabelType(labelName, labelType) != null;
    }

    @Override
    public boolean isExistByLabelId(Integer labelId) {
        return labelMapper.findByLabelId(labelId) != null;
    }

    @Override
    public List<Integer> getLabelIdOrInsert(List<Label> labels) {
        List<Integer> ret = new ArrayList<>();
        for (Label label : labels) {
            Label label1 = labelMapper.findByLabelNameAndLabelType(label.getLabelName(), label.getLabelType());
            if(label1 == null){
                label.setLabelId(null);
                labelMapper.insertSelective(label);
                ret.add(label.getLabelId());
            } else{
                ret.add(label1.getLabelId());
            }
        }
        return ret;
    }

    @Override
    public List<Label> getLabelByLabelType(Integer labelType) {
        return labelMapper.findByLabelType(labelType);
    }


}

