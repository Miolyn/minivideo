package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.LikeMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapMapper {
    int deleteByPrimaryKey(Integer likeMapId);

    int insert(LikeMap record);

    int insertSelective(LikeMap record);

    LikeMap selectByPrimaryKey(Integer likeMapId);

    int updateByPrimaryKeySelective(LikeMap record);

    int updateByPrimaryKey(LikeMap record);
}