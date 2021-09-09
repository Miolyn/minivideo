package cn.tju.minivideo.dao;
import java.util.List;

import cn.tju.minivideo.entity.Activity;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;

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

    int updateCommentNumByActivityId(@Param("activityId")Integer activityId);


    List<Activity> findByCommunityIdOrderByCreatedAt(@Param("communityId")Integer communityId);

    List<Activity> findByCommunityIdOrderByLikeNum(@Param("communityId")Integer communityId);

    List<Activity> getAllOrderByCreatedAt();

}