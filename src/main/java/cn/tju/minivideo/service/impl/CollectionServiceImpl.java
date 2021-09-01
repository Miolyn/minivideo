package cn.tju.minivideo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.CollectionMapper;
import cn.tju.minivideo.entity.Collection;
import cn.tju.minivideo.service.CollectionService;
@Service
public class CollectionServiceImpl implements CollectionService{

    @Resource
    private CollectionMapper collectionMapper;

    @Override
    public int deleteByPrimaryKey(Integer collectionId) {
        return collectionMapper.deleteByPrimaryKey(collectionId);
    }

    @Override
    public int insert(Collection record) {
        return collectionMapper.insert(record);
    }

    @Override
    public int insertSelective(Collection record) {
        return collectionMapper.insertSelective(record);
    }

    @Override
    public Collection selectByPrimaryKey(Integer collectionId) {
        return collectionMapper.selectByPrimaryKey(collectionId);
    }

    @Override
    public int updateByPrimaryKeySelective(Collection record) {
        return collectionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Collection record) {
        return collectionMapper.updateByPrimaryKey(record);
    }

}
