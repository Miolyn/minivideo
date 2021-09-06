package cn.tju.minivideo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.entity.BulletScreen;
import cn.tju.minivideo.dao.BulletScreenMapper;
import cn.tju.minivideo.service.BulletScreenService;

@Service
public class BulletScreenServiceImpl implements BulletScreenService {

    @Resource
    private BulletScreenMapper bulletScreenMapper;

    @Override
    public int deleteByPrimaryKey(Integer bulletScreenId) {
        return bulletScreenMapper.deleteByPrimaryKey(bulletScreenId);
    }

    @Override
    public int insert(BulletScreen record) {
        return bulletScreenMapper.insert(record);
    }

    @Override
    public int insertSelective(BulletScreen record) {
        return bulletScreenMapper.insertSelective(record);
    }

    @Override
    public BulletScreen selectByPrimaryKey(Integer bulletScreenId) {
        return bulletScreenMapper.selectByPrimaryKey(bulletScreenId);
    }

    @Override
    public int updateByPrimaryKeySelective(BulletScreen record) {
        return bulletScreenMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BulletScreen record) {
        return bulletScreenMapper.updateByPrimaryKey(record);
    }

}


