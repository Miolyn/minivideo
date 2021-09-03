package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.LikeMap;
public interface LikeMapService{


    int deleteByPrimaryKey(Integer likeMapId);

    int insert(LikeMap record);

    int insertSelective(LikeMap record);

    LikeMap selectByPrimaryKey(Integer likeMapId);

    int updateByPrimaryKeySelective(LikeMap record);

    int updateByPrimaryKey(LikeMap record);

    boolean isExistByFromIdAndToIdAndLikeType(String fromId, Integer toId, Integer likeType);
}
