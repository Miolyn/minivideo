package cn.tju.minivideo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.entity.VideoGoodsRecommend;
import cn.tju.minivideo.dao.VideoGoodsRecommendMapper;
import cn.tju.minivideo.dao.VideoMapper;
import cn.tju.minivideo.service.VideoGoodsRecommendService;

@Service
public class VideoGoodsRecommendServiceImpl implements VideoGoodsRecommendService {

    @Resource
    private VideoGoodsRecommendMapper videoGoodsRecommendMapper;

    @Override
    public int deleteByPrimaryKey(Integer videoGoodsRecommendId) {
        return videoGoodsRecommendMapper.deleteByPrimaryKey(videoGoodsRecommendId);
    }

    @Override
    public int insert(VideoGoodsRecommend record) {
        return videoGoodsRecommendMapper.insert(record);
    }

    @Override
    public int insertSelective(VideoGoodsRecommend record) {
        return videoGoodsRecommendMapper.insertSelective(record);
    }

    @Override
    public VideoGoodsRecommend selectByPrimaryKey(Integer videoGoodsRecommendId) {
        return videoGoodsRecommendMapper.selectByPrimaryKey(videoGoodsRecommendId);
    }

    @Override
    public int updateByPrimaryKeySelective(VideoGoodsRecommend record) {
        return videoGoodsRecommendMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(VideoGoodsRecommend record) {
        return videoGoodsRecommendMapper.updateByPrimaryKey(record);
    }

    @Override 
    public List<VideoGoodsRecommend> selectByVideoId(Integer videoId) {
        return videoGoodsRecommendMapper.selectByVideoId(videoId);
    }
}

