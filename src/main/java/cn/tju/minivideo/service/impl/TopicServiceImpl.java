package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.TopicMapper;
import cn.tju.minivideo.entity.Topic;
import cn.tju.minivideo.service.TopicService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService{

    @Resource
    private TopicMapper topicMapper;

    @Override
    public int deleteByPrimaryKey(Integer topicId) {
        return topicMapper.deleteByPrimaryKey(topicId);
    }

    @Override
    public int insert(Topic record) {
        return topicMapper.insert(record);
    }

    @Override
    public int insertSelective(Topic record) {
        return topicMapper.insertSelective(record);
    }

    @Override
    public Topic selectByPrimaryKey(Integer topicId) {
        return topicMapper.selectByPrimaryKey(topicId);
    }

    @Override
    public int updateByPrimaryKeySelective(Topic record) {
        return topicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Topic record) {
        return topicMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Integer> getTopicIdsOrInsert(List<String> topics, String userId) {
        List<Integer> ids = new ArrayList<>();
        for (String topic : topics) {
            Topic topic1 = topicMapper.findByTopicName(topic);
            if (topic1 == null){
                topic1 = new Topic(topic, userId);
                topicMapper.insertSelective(topic1);
            }
            ids.add(topic1.getTopicId());
        }
        return ids;
    }

    @Override
    public List<Topic> getHotTopic() {

        return topicMapper.findOrderByActivityNum();
    }

    @Override
    public Topic getTopicByTopicName(String topicName) {
        Topic topic = topicMapper.findByTopicName(topicName);
        if(topic == null){
            throw new ServiceException(MsgEnums.ITEM_NOT_EXIST);
        }
        return topic;
    }

}
