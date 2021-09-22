package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.entity.Activity;
import cn.tju.minivideo.dao.ActivityMapper;
import cn.tju.minivideo.service.ActivityService;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public int deleteByPrimaryKey(Integer activityId) {
        return activityMapper.deleteByPrimaryKey(activityId);
    }

    @Override
    public int insert(Activity record) {
        return activityMapper.insert(record);
    }

    @Override
    public int insertSelective(Activity record) {
        return activityMapper.insertSelective(record);
    }

    @Override
    public Activity selectByPrimaryKey(Integer activityId) {
        return activityMapper.selectByPrimaryKey(activityId);
    }

    @Override
    public int updateByPrimaryKeySelective(Activity record) {
        return activityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Activity record) {
        return activityMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Activity record) {
        return activityMapper.updateByPrimaryKey(record);
    }

    @Override
    public Activity getActivityByActivityId(Integer activityId) {
        Activity activity = activityMapper.findByActivityId(activityId);
        if (activity == null) {
            throw new ServiceException(MsgEnums.ITEM_NOT_EXIST);
        }
        return activity;
    }

    @Override
    public boolean isActivityExistByActivityId(Integer activityId) {
        return getActivityByActivityId(activityId) != null;
    }

    @Override
    public void lockActivityByActivityId(Integer activityId) {
        activityMapper.findByActivityIdForUpdate(activityId);
    }

    @Override
    public int addLikeNumByActivityId(Integer activityId) {
        return activityMapper.updateLikeNumByActivityId(activityId);
    }

    @Override
    public int addCollectNumByActivityId(Integer activityId) {
        return activityMapper.updateCollectNumByActivityId(activityId);
    }

    @Override
    public int addCommentNumByActivityId(Integer activityId) {
        return activityMapper.updateCommentNumByActivityId(activityId);
    }

    @Override
    public PageInfo<Activity> getCommunityActivitiesByEssenceWithPaginatorSortByMethod(Integer communityId, Integer essence, Integer page, Integer pageSize, Integer sortMethod) {
        PageHelper.startPage(page, pageSize);
        PageInfo<Activity> pageInfo;
        if (essence.equals(Constants.ActivityConst.ActivityAnyEssence)) {
            if (sortMethod.equals(SortMethod.SortByTimeDesc)) {
                pageInfo = new PageInfo<>(activityMapper.findByCommunityIdOrderByCreatedAt(communityId));
            } else if (sortMethod.equals(SortMethod.SortByLikeNumDesc)) {
                pageInfo = new PageInfo<>(activityMapper.findByCommunityIdOrderByLikeNum(communityId));
            } else {
                throw new ServiceException(MsgEnums.VALIDATION_ERROR);
            }
        } else {
            if (sortMethod.equals(SortMethod.SortByTimeDesc)) {
                pageInfo = new PageInfo<>(activityMapper.findByCommunityIdAndIsEssenceOrderByCreatedAt(communityId, essence));
            } else if (sortMethod.equals(SortMethod.SortByLikeNumDesc)) {
                pageInfo = new PageInfo<>(activityMapper.findByCommunityIdAndIsEssenceOrderByLikeNum(communityId, essence));
            } else {
                throw new ServiceException(MsgEnums.VALIDATION_ERROR);
            }
        }

        return pageInfo;
    }

    @Override
    public List<Activity> getCommunityActivitiesByEssenceSortByMethod(Integer communityId, Integer essence, Integer sortMethod) {
        if (essence.equals(Constants.ActivityConst.ActivityAnyEssence)) {
            if (sortMethod.equals(SortMethod.SortByTimeDesc)) {
                return activityMapper.findByCommunityIdOrderByCreatedAt(communityId);
            } else if (sortMethod.equals(SortMethod.SortByLikeNumDesc)) {
                return activityMapper.findByCommunityIdOrderByLikeNum(communityId);
            } else {
                throw new ServiceException(MsgEnums.VALIDATION_ERROR);
            }
        } else {
            if (sortMethod.equals(SortMethod.SortByTimeDesc)) {
                return activityMapper.findByCommunityIdAndIsEssenceOrderByCreatedAt(communityId, essence);
            } else if (sortMethod.equals(SortMethod.SortByLikeNumDesc)) {
                return activityMapper.findByCommunityIdAndIsEssenceOrderByLikeNum(communityId, essence);
            } else {
                throw new ServiceException(MsgEnums.VALIDATION_ERROR);
            }
        }
    }

    @Override
    public PageInfo<Activity> getActivitiesWithPaginator(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(activityMapper.getAllOrderByCreatedAt());
    }

    @Override
    public List<Activity> getActivities() {
        return activityMapper.getAllOrderByCreatedAt();
    }

    @Override
    public String getUserIdOfActivityByActivityId(Integer activityId) {
        String userId = activityMapper.findUserIdByActivityId(activityId);
        if (userId == null || userId.equals("")) {
            throw new ServiceException(MsgEnums.ITEM_NOT_EXIST);
        }
        return userId;
    }

    @Override
    public PageInfo<Activity> getActivitiesByTopicIdWithPaginatorSortByMethod(Integer topicId, Integer sortMethod, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        if (sortMethod.equals(SortMethod.SortByTimeDesc)) {
            return new PageInfo<>(activityMapper.findByTopicIdOrderByCreatedAt(topicId));
        } else if (sortMethod.equals(SortMethod.SortByLikeNumDesc)) {
            return new PageInfo<>(activityMapper.findByTopicIdOrderByLikeNum(topicId));
        } else {
            throw new ServiceException(MsgEnums.ACTION_NOT_FOUND);
        }
    }

    @Override
    public List<Activity> getActivitiesByUserIdSortByMethod(String userId, Integer sortMethod) {
        if(sortMethod.equals(SortMethod.SortByTimeDesc)){
            return activityMapper.findByUserIdOrderByCreatedAt(userId);
        } else{
            throw new ServiceException(MsgEnums.ACTION_NOT_FOUND);
        }
    }

}




