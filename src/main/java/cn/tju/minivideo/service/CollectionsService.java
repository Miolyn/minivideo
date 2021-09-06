package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Collections;
import com.github.pagehelper.PageInfo;

public interface CollectionsService{


    int deleteByPrimaryKey(Integer collectionId);

    int insert(Collections record);

    int insertSelective(Collections record);

    Collections selectByPrimaryKey(Integer collectionId);

    int updateByPrimaryKeySelective(Collections record);

    int updateByPrimaryKey(Collections record);

    boolean isExistByItemIdAndUserIdAnItemType(Integer itemId, String userId, Integer itemType);

    PageInfo<Collections> getCollectionsByItemTypeAndUserIdWithPaginator(Integer itemType, String userId, Integer page, Integer pageSize);

}
