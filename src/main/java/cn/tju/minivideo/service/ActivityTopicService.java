package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.ActivityTopic;

import java.util.List;

public interface ActivityTopicService{


    int deleteByPrimaryKey(Integer activityTopicId);

    int insert(ActivityTopic record);

    int insertSelective(ActivityTopic record);

    ActivityTopic selectByPrimaryKey(Integer activityTopicId);

    int updateByPrimaryKeySelective(ActivityTopic record);

    int updateByPrimaryKey(ActivityTopic record);


    void insertActivityTopicByTopicIdsAndActivityId(List<Integer> topicIds, Integer activityId);

}
