package cn.tju.minivideo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.tju.minivideo.entity.Video;
import cn.tju.minivideo.dao.VideoMapper;
import cn.tju.minivideo.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoMapper videoMapper;

    @Override
    public int deleteByPrimaryKey(Integer videoId) {
        return videoMapper.deleteByPrimaryKey(videoId);
    }

    @Override
    public int insert(Video record) {
        return videoMapper.insert(record);
    }

    @Override
    public int insertSelective(Video record) {
        return videoMapper.insertSelective(record);
    }

    @Override
    public Video selectByPrimaryKey(Integer videoId) {
        return videoMapper.selectByPrimaryKey(videoId);
    }

    @Override
    public int updateByPrimaryKeySelective(Video record) {
        return videoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Video record) {
        return videoMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Video> getVideosByUserIdWithPaginator(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(videoMapper.findByUserId(userId));
    }

    @Override
    public int addVideoPlayNumByVideoId(Integer videoId) {
        return videoMapper.updatePlayNumByVideoId(videoId);

    }


}



