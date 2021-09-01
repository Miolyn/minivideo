package cn.tju.minivideo.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.tju.minivideo.entity.Video;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoMapper {
    int deleteByPrimaryKey(Integer videoId);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Integer videoId);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    List<Video> findByUserId(@Param("userId")String userId);

    int updatePlayNumByVideoId(@Param("videoId")Integer videoId);





}