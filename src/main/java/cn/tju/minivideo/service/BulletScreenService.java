package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.BulletScreen;

public interface BulletScreenService {


    int deleteByPrimaryKey(Integer bulletScreenId);

    int insert(BulletScreen record);

    int insertSelective(BulletScreen record);

    BulletScreen selectByPrimaryKey(Integer bulletScreenId);

    int updateByPrimaryKeySelective(BulletScreen record);

    int updateByPrimaryKey(BulletScreen record);

}


