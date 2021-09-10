package cn.tju.minivideo.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

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

    CommunityMember findByCommunityIdAndUserId(@Param("communityId")Integer communityId,@Param("userId")String userId);

    List<CommunityMember> findByUserIdOrderByCreatedAt(@Param("userId")String userId);
}