package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.service.CollectionsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.entity.Collections;
import cn.tju.minivideo.dao.CollectionsMapper;

@Service
public class CollectionsServiceImpl implements CollectionsService {

    @Resource
    private CollectionsMapper collectionsMapper;

    @Override
    public int deleteByPrimaryKey(Integer collectionId) {
        return collectionsMapper.deleteByPrimaryKey(collectionId);
    }

    @Override
    public int insert(Collections record) {
        return collectionsMapper.insert(record);
    }

    @Override
    public int insertSelective(Collections record) {
        return collectionsMapper.insertSelective(record);
    }

    @Override
    public Collections selectByPrimaryKey(Integer collectionId) {
        return collectionsMapper.selectByPrimaryKey(collectionId);
    }

    @Override
    public int updateByPrimaryKeySelective(Collections record) {
        return collectionsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Collections record) {
        return collectionsMapper.updateByPrimaryKey(record);
    }

    @Override
    public boolean isExistByItemIdAndUserIdAnItemType(Integer itemId, String userId, Integer itemType) {

        return collectionsMapper.findByItemIdAndUserIdAndItemType(itemId, userId, itemType) != null;
    }

    @Override
    public PageInfo<Collections> getCollectionsByItemTypeAndUserIdWithPaginator(Integer itemType, String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        PageInfo<Collections> collectionsPageInfo = new PageInfo<>(collectionsMapper.findByItemTypeAndUserId(itemType, userId));
        return collectionsPageInfo;
    }

}

