package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Video;

public interface VideoService {


    int deleteByPrimaryKey(Integer videoId);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Integer videoId);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

}


