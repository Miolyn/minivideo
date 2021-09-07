package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.Label;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LabelMapper {
    int deleteByPrimaryKey(Integer labelId);

    int insert(Label record);

    int insertSelective(Label record);

    Label selectByPrimaryKey(Integer labelId);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);
}