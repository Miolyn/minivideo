package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.entity.Activity;
import cn.tju.minivideo.dao.ActivityMapper;
import cn.tju.minivideo.service.ActivityService;

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
    public PageInfo<Activity> getCommunityActivitiesWithPaginatorSortByMethod(Integer communityId, Integer page, Integer pageSize, Integer sortMethod) {
        PageHelper.startPage(page, pageSize);
        PageInfo<Activity> pageInfo;
        if (sortMethod.equals(SortMethod.SortByTimeDesc)){
            pageInfo = new PageInfo<>(activityMapper.findByCommunityIdOrderByCreatedAt(communityId));
        } else if (sortMethod.equals(SortMethod.SortByLikeNumDesc)){
            pageInfo = new PageInfo<>(activityMapper.findByCommunityIdOrderByLikeNum(communityId));
        } else {
            throw new ServiceException(MsgEnums.VALIDATION_ERROR);
        }
        return pageInfo;
    }

    @Override
    public PageInfo<Activity> getActivitiesWithPaginator(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(activityMapper.getAllOrderByCreatedAt());
    }

}


