package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.CommunityMember;
public interface CommunityMemberService{


    int deleteByPrimaryKey(Integer communityMemberId);

    int insert(CommunityMember record);

    int insertSelective(CommunityMember record);

    CommunityMember selectByPrimaryKey(Integer communityMemberId);

    int updateByPrimaryKeySelective(CommunityMember record);

    int updateByPrimaryKey(CommunityMember record);

    boolean isExistByUserIdAndCommunityId(String userId, Integer communityId);
}
