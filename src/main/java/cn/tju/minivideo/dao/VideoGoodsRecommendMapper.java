package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.VideoGoodsRecommend;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoGoodsRecommendMapper {
    int deleteByPrimaryKey(Integer videoGoodsRecommendId);

    int insert(VideoGoodsRecommend record);

    int insertSelective(VideoGoodsRecommend record);

    VideoGoodsRecommend selectByPrimaryKey(Integer videoGoodsRecommendId);

    int updateByPrimaryKeySelective(VideoGoodsRecommend record);

    int updateByPrimaryKey(VideoGoodsRecommend record);
}