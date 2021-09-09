package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.ActivityTopicMapper;
import cn.tju.minivideo.entity.ActivityTopic;
import cn.tju.minivideo.service.ActivityTopicService;

import java.util.List;

@Service
public class ActivityTopicServiceImpl implements ActivityTopicService{

    @Resource
    private ActivityTopicMapper activityTopicMapper;

    @Override
    public int deleteByPrimaryKey(Integer activityTopicId) {
        return activityTopicMapper.deleteByPrimaryKey(activityTopicId);
    }

    @Override
    public int insert(ActivityTopic record) {
        return activityTopicMapper.insert(record);
    }

    @Override
    public int insertSelective(ActivityTopic record) {
        return activityTopicMapper.insertSelective(record);
    }

    @Override
    public ActivityTopic selectByPrimaryKey(Integer activityTopicId) {
        return activityTopicMapper.selectByPrimaryKey(activityTopicId);
    }

    @Override
    public int updateByPrimaryKeySelective(ActivityTopic record) {
        return activityTopicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ActivityTopic record) {
        return activityTopicMapper.updateByPrimaryKey(record);
    }

    @Override
    public void insertActivityTopicByTopicIdsAndActivityId(List<Integer> topicIds, Integer activityId) {
        for (Integer topicId : topicIds) {
            ActivityTopic activityTopic = new ActivityTopic(topicId, activityId);
            if(activityTopicMapper.insertSelective(activityTopic) != 1){
                throw new ServiceException(MsgEnums.INTERNAL_ERROR);
            }
        }
    }

}
