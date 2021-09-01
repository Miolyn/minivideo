package cn.tju.minivideo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.LikeMapMapper;
import cn.tju.minivideo.entity.LikeMap;
import cn.tju.minivideo.service.LikeMapService;
@Service
public class LikeMapServiceImpl implements LikeMapService{

    @Resource
    private LikeMapMapper likeMapMapper;

    @Override
    public int deleteByPrimaryKey(Integer likeMapId) {
        return likeMapMapper.deleteByPrimaryKey(likeMapId);
    }

    @Override
    public int insert(LikeMap record) {
        return likeMapMapper.insert(record);
    }

    @Override
    public int insertSelective(LikeMap record) {
        return likeMapMapper.insertSelective(record);
    }

    @Override
    public LikeMap selectByPrimaryKey(Integer likeMapId) {
        return likeMapMapper.selectByPrimaryKey(likeMapId);
    }

    @Override
    public int updateByPrimaryKeySelective(LikeMap record) {
        return likeMapMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(LikeMap record) {
        return likeMapMapper.updateByPrimaryKey(record);
    }

}
