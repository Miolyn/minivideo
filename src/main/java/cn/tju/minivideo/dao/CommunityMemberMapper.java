package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.CommunityMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityMemberMapper {
    int deleteByPrimaryKey(Integer communityMemberId);

    int insert(CommunityMember record);

    int insertSelective(CommunityMember record);

    CommunityMember selectByPrimaryKey(Integer communityMemberId);

    int updateByPrimaryKeySelective(CommunityMember record);

    int updateByPrimaryKey(CommunityMember record);
}