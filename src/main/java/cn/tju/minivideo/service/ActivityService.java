package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Activity;
import com.github.pagehelper.PageInfo;

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

    PageInfo<Activity> getCommunityActivitiesWithPaginatorSortByMethod(Integer communityId, Integer page, Integer pageSize, Integer sortMethod);

    PageInfo<Activity> getActivitiesWithPaginator(Integer page, Integer pageSize);
}


