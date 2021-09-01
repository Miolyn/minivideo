package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Collection;
public interface CollectionService{


    int deleteByPrimaryKey(Integer collectionId);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Integer collectionId);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);

}
