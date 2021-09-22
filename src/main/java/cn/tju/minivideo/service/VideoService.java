package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Video;
import com.github.pagehelper.PageInfo;

public interface VideoService {

    public interface SortMethod {
        Integer SortByTimeDesc = 1;
        Integer SortByPlayNumDesc = 2;
        Integer SortByLikeNumDesc = 3;
    }

    int deleteByPrimaryKey(Integer videoId);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Integer videoId);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);


    PageInfo<Video> getVideosByUserIdWithPaginatorOrderByCreatedAtDesc(String userId, Integer page, Integer pageSize);

    PageInfo<Video> getVideosByUserIdWithPaginatorOrderByPlayNum(String userId, Integer page, Integer pageSize);

    PageInfo<Video> getVideosByUserIdWithPaginatorSortByMethod(String userId, Integer page, Integer pageSize, Integer sortMethod);

    PageInfo<Video> getVideosByVideoTypeWithPaginatorSortByMethod(Integer videoType, Integer page, Integer pageSize, Integer sortMethod);

    int addVideoPlayNumByVideoId(Integer videoId);

    int addVideoLikeNumByVideoId(Integer videoId);

    int addVideoCommentNumByVideoId(Integer videoId);

    int addVideoCollectNumByVideoId(Integer videoId);

    boolean checkPermissionToUpdateVideoProfile(Integer videoId, String userId);

    int deleteVideoByVideoIdLogical(Integer videoId);

    boolean isVideoExistByVideoId(Integer videoId);

    void lockVideoByVideoId(Integer videoId);

    String getUserIdOfrVideoByVideoId(Integer videoId);

    PageInfo<Video> searchVideoByKeyOnTitleAndIntroductionWithPaginator(String key, Integer sortMethod, Integer page, Integer pageSize);
}







