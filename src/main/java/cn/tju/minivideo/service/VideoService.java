package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Video;
import com.github.pagehelper.PageInfo;

public interface VideoService {


    int deleteByPrimaryKey(Integer videoId);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Integer videoId);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    PageInfo<Video> getVideosByUserIdWithPaginator(String userId, Integer page, Integer pageSize);

    int addVideoPlayNumByVideoId(Integer videoId);
}



