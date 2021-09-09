package cn.tju.minivideo.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.tju.minivideo.entity.Community;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityMapper {
    int deleteByPrimaryKey(Integer communityId);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(Integer communityId);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);

    Community findByCommunityId(@Param("communityId")Integer communityId);


}