package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Topic;

import java.util.List;

public interface TopicService{


    int deleteByPrimaryKey(Integer topicId);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer topicId);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);

    List<Integer> getTopicIdsOrInsert(List<String> topics, String userId);

    List<Topic> getHotTopic();


    Topic getTopicByTopicName(String topicName);
}
