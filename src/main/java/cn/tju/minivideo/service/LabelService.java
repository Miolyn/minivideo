package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Label;
public interface LabelService{


    int deleteByPrimaryKey(Integer labelId);

    int insert(Label record);

    int insertSelective(Label record);

    Label selectByPrimaryKey(Integer labelId);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);

}
