package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.Collections;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectionsMapper {
    int deleteByPrimaryKey(Integer collectionId);

    int insert(Collections record);

    int insertSelective(Collections record);

    Collections selectByPrimaryKey(Integer collectionId);

    int updateByPrimaryKeySelective(Collections record);

    int updateByPrimaryKey(Collections record);
}