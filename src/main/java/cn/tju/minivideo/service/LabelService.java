package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Label;

import java.util.List;

public interface LabelService {


    int deleteByPrimaryKey(Integer labelId);

    int insert(Label record);

    int insertSelective(Label record);

    Label selectByPrimaryKey(Integer labelId);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);

    boolean isExistByLabelNameAndLabelType(String labelName, Integer labelType);

    boolean isExistByLabelId(Integer labelId);

    List<Integer> getLabelIdOrInsert(List<Label> labels);

    List<Label> getLabelByLabelType(Integer labelType);
}

