package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.Activity;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

@Mapper
public interface ActivityMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKeyWithBLOBs(Activity record);

    int updateByPrimaryKey(Activity record);

    Activity findByActivityId(@Param("activityId") Integer activityId);

    Activity findByActivityIdForUpdate(@Param("activityId") Integer activityId);

    int updateLikeNumByActivityId(@Param("activityId") Integer activityId);

    int updateCollectNumByActivityId(@Param("activityId") Integer activityId);

    int updateCommentNumByActivityId(@Param("activityId") Integer activityId);

    List<Activity> findByCommunityIdOrderByCreatedAt(@Param("communityId") Integer communityId);

    List<Activity> findByCommunityIdAndIsEssenceOrderByCreatedAt(@Param("communityId")Integer communityId,@Param("isEssence")Integer isEssence);

    List<Activity> findByCommunityIdOrderByLikeNum(@Param("communityId") Integer communityId);

    List<Activity> findByCommunityIdAndIsEssenceOrderByLikeNum(@Param("communityId")Integer communityId,@Param("isEssence")Integer isEssence);



    List<Activity> getAllOrderByCreatedAt();

    String findUserIdByActivityId(@Param("activityId") Integer activityId);

    List<Activity> findByTopicIdOrderByCreatedAt(Integer topicId);

    List<Activity> findByTopicIdOrderByLikeNum(Integer topicId);
}