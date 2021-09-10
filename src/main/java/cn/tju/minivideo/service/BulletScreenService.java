package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.BulletScreen;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BulletScreenService {


    int deleteByPrimaryKey(Integer bulletScreenId);

    int insert(BulletScreen record);

    int insertSelective(BulletScreen record);

    BulletScreen selectByPrimaryKey(Integer bulletScreenId);

    int updateByPrimaryKeySelective(BulletScreen record);

    int updateByPrimaryKey(BulletScreen record);

    List<BulletScreen> findAllByVideoId(Integer videoId);

    PageInfo<BulletScreen> findByVideoIdWithPaginator(Integer videoId, Integer page, Integer pageSize);
}



