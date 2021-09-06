package cn.tju.minivideo.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    Collections findByItemIdAndUserIdAndItemType(@Param("itemId") Integer itemId, @Param("userId") String userId, @Param("itemType") Integer itemType);

    List<Collections> findByItemTypeAndUserId(@Param("itemType")Integer itemType,@Param("userId")String userId);



}