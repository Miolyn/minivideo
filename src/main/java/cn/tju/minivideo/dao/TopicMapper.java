package cn.tju.minivideo.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.tju.minivideo.entity.Topic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopicMapper {
    int deleteByPrimaryKey(Integer topicId);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer topicId);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);

    Topic findByTopicName(@Param("topicName")String topicName);


    List<Topic> findOrderByActivityNum();

}