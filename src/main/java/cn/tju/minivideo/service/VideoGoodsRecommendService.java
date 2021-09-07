package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.VideoGoodsRecommend;

public interface VideoGoodsRecommendService {


    int deleteByPrimaryKey(Integer videoGoodsRecommendId);

    int insert(VideoGoodsRecommend record);

    int insertSelective(VideoGoodsRecommend record);

    VideoGoodsRecommend selectByPrimaryKey(Integer videoGoodsRecommendId);

    int updateByPrimaryKeySelective(VideoGoodsRecommend record);

    int updateByPrimaryKey(VideoGoodsRecommend record);

}

