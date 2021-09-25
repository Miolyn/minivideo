package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.Video;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

@Mapper
public interface VideoMapper {
    int deleteByPrimaryKey(Integer videoId);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Integer videoId);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    List<Video> findByUserId(@Param("userId") String userId);

    List<Video> findByUserIdOrderByCreatedAt(@Param("userId") String userId);

    List<Video> findByUserIdOrderByPlayNum(@Param("userId") String userId);

    List<Video> findByVideoTypeOrderByCreatedAt(@Param("videoType")Integer videoType);

	List<Video> findByVideoTypeOrderByLikeNum(@Param("videoType")Integer videoType);

	List<Video> findByVideoTypeOrderByPlayNum(@Param("videoType")Integer videoType);

	List<Video> findOrderByCreatedAt();

	List<Video> findOrderByLikeNum();

	List<Video> findOrderByPlayNum();




    int updatePlayNumByVideoId(@Param("videoId") Integer videoId);

    int updateLikeNumByVideoId(@Param("videoId") Integer videoId);

    int updateCommentNumByVideoId(@Param("videoId") Integer videoId);

    int updateCollectNumByVideoId(@Param("videoId") Integer videoId);

    Video findByVideoIdForUpdate(@Param("videoId") Integer videoId);

    int deleteByVideoIdLogical(@Param("videoId") Integer videoId);

    String getUserIdByVideoId(@Param("videoId")Integer videoId);

    List<Video> searchOnTitleAndIntroductionOrderByCreatedAt(@Param("key")String key);

    List<Video> searchOnTitleAndIntroductionOrderByPlayNum(@Param("key")String key);

    List<Video> searchOnTitleAndIntroductionOrderByLikeNum(@Param("key")String key);

    Video findByVideoId(@Param("videoId")Integer videoId);


}