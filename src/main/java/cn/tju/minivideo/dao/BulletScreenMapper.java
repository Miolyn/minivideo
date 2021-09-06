package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.BulletScreen;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BulletScreenMapper {
    int deleteByPrimaryKey(Integer bulletScreenId);

    int insert(BulletScreen record);

    int insertSelective(BulletScreen record);

    BulletScreen selectByPrimaryKey(Integer bulletScreenId);

    int updateByPrimaryKeySelective(BulletScreen record);

    int updateByPrimaryKey(BulletScreen record);
}