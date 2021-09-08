package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.VideoGoodsRecommend;
import java.util.List; 

public interface VideoGoodsRecommendService {


    int deleteByPrimaryKey(Integer videoGoodsRecommendId);

    int insert(VideoGoodsRecommend record);

    int insertSelective(VideoGoodsRecommend record);

    VideoGoodsRecommend selectByPrimaryKey(Integer videoGoodsRecommendId);

    int updateByPrimaryKeySelective(VideoGoodsRecommend record);

    int updateByPrimaryKey(VideoGoodsRecommend record);

    List<VideoGoodsRecommend> selectByVideoId(Integer videoId);
}

