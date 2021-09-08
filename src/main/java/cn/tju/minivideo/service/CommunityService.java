package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Community;
public interface CommunityService{


    int deleteByPrimaryKey(Integer communityId);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(Integer communityId);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);

}
