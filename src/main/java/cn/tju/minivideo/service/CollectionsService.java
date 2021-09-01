package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Collections;
public interface CollectionsService{


    int deleteByPrimaryKey(Integer collectionId);

    int insert(Collections record);

    int insertSelective(Collections record);

    Collections selectByPrimaryKey(Integer collectionId);

    int updateByPrimaryKeySelective(Collections record);

    int updateByPrimaryKey(Collections record);

}
