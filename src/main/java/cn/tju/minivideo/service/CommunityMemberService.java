package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Community;
import cn.tju.minivideo.entity.CommunityMember;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommunityMemberService{


    int deleteByPrimaryKey(Integer communityMemberId);

    int insert(CommunityMember record);

    int insertSelective(CommunityMember record);

    CommunityMember selectByPrimaryKey(Integer communityMemberId);

    int updateByPrimaryKeySelective(CommunityMember record);

    int updateByPrimaryKey(CommunityMember record);

    boolean isExistByUserIdAndCommunityId(String userId, Integer communityId);

    PageInfo<CommunityMember> getCommunitiesByUserIdWithPaginator(String userId, Integer page, Integer pageSize);
    List<CommunityMember> getCommunitiesByUserId(String userId);

}
