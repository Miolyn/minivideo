package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
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
    public PageInfo<Video> getVideosByUserIdWithPaginatorOrderByCreatedAtDesc(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(videoMapper.findByUserIdOrderByCreatedAt(userId));
    }

    @Override
    public PageInfo<Video> getVideosByUserIdWithPaginatorOrderByPlayNum(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(videoMapper.findByUserIdOrderByPlayNum(userId));
    }

    @Override
    public PageInfo<Video> getVideosByUserIdWithPaginatorSortByMethod(String userId, Integer page, Integer pageSize, Integer sortMethod) {
        PageHelper.startPage(page, pageSize);
        if (sortMethod.equals(SortMethod.SortByTimeDesc)) {
            return new PageInfo<>(videoMapper.findByUserIdOrderByCreatedAt(userId));
        } else if (sortMethod.equals(SortMethod.SortByPlayNumDesc)) {
            return new PageInfo<>(videoMapper.findByUserIdOrderByPlayNum(userId));
        } else {
            throw new ServiceException(MsgEnums.ACTION_NOT_FOUND);
        }
    }

    @Override
    public int addVideoPlayNumByVideoId(Integer videoId) {
        return videoMapper.updatePlayNumByVideoId(videoId);

    }

    @Override
    public int addVideoLikeNumByVideoId(Integer videoId) {
        return videoMapper.updateLikeNumByVideoId(videoId);
    }

    @Override
    public int addVideoCommentNumByVideoId(Integer videoId) {

        return videoMapper.updateCommentNumByVideoId(videoId);
    }

    @Override
    public int addVideoCollectNumByVideoId(Integer videoId) {
        return videoMapper.updateCollectNumByVideoId(videoId);
    }

    @Override
    public boolean checkPermissionToUpdateVideoProfile(Integer videoId, String userId) {
        Video video = videoMapper.selectByPrimaryKey(videoId);
        if (video == null || !video.getUserId().equals(userId)) {
            return false;
        }
        return true;
    }

    @Override
    public int deleteVideoByVideoIdLogical(Integer videoId) {
        return videoMapper.deleteByVideoIdLogical(videoId);
    }

    @Override
    public boolean isVideoExistByVideoId(Integer videoId) {
        return videoMapper.selectByPrimaryKey(videoId) != null;
    }

    @Override
    public void lockVideoByVideoId(Integer videoId) {
        Video video = videoMapper.findByVideoIdForUpdate(videoId);
        if (video == null) {
            throw new ServiceException(MsgEnums.ITEM_NOT_EXIST);
        }
    }


}







