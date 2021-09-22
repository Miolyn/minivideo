package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Activity;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ActivityService {
    interface SortMethod {
        Integer SortByTimeDesc = 1;
        Integer SortByLikeNumDesc = 2;
    }

    int deleteByPrimaryKey(Integer activityId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKeyWithBLOBs(Activity record);

    int updateByPrimaryKey(Activity record);

    Activity getActivityByActivityId(Integer activityId);

    boolean isActivityExistByActivityId(Integer activityId);

    void lockActivityByActivityId(Integer activityId);

    int addLikeNumByActivityId(Integer activityId);

    int addCollectNumByActivityId(Integer activityId);

    int addCommentNumByActivityId(Integer activityId);

    PageInfo<Activity> getCommunityActivitiesByEssenceWithPaginatorSortByMethod(Integer communityId, Integer essence, Integer page, Integer pageSize, Integer sortMethod);

    List<Activity> getCommunityActivitiesByEssenceSortByMethod(Integer communityId, Integer essence, Integer sortMethod);

    PageInfo<Activity> getActivitiesWithPaginator(Integer page, Integer pageSize);

    List<Activity> getActivities();

    String getUserIdOfActivityByActivityId(Integer activityId);

    PageInfo<Activity> getActivitiesByTopicIdWithPaginatorSortByMethod(Integer topicId, Integer sortMethod, Integer page, Integer pageSize);

    List<Activity> getActivitiesByUserIdSortByMethod(String userId, Integer sortMethod);
}




