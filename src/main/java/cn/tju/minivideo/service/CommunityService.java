package cn.tju.minivideo.service;

import cn.tju.minivideo.dto.CommunityDto;
import cn.tju.minivideo.entity.Community;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommunityService{


    int deleteByPrimaryKey(Integer communityId);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(Integer communityId);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);


    Community getCommunityByCommunityId(Integer communityId);

    CommunityDto getCommunityDtoByCommunityId(Integer communityId);

    CommunityDto getCommunityDtoByCommunity(Community community);

    PageInfo<Community> getCommunitiesWithPaginatorOrderByCreatedAt(Integer page, Integer pageSize);
    List<Community> getCommunitiesOrderByCreatedAt();

    void lockCommunityByCommunityId(Integer communityId);

    int addCommunityActivityNumByCommunityId(Integer communityId);

    int addCommunityMemberNumByCommunityId(Integer communityId);
}
