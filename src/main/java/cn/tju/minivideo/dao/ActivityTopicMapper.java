package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.ActivityTopic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityTopicMapper {
    int deleteByPrimaryKey(Integer activityTopicId);

    int insert(ActivityTopic record);

    int insertSelective(ActivityTopic record);

    ActivityTopic selectByPrimaryKey(Integer activityTopicId);

    int updateByPrimaryKeySelective(ActivityTopic record);

    int updateByPrimaryKey(ActivityTopic record);
}