package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.entity.Community;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.CommunityMemberMapper;
import cn.tju.minivideo.entity.CommunityMember;
import cn.tju.minivideo.service.CommunityMemberService;
@Service
public class CommunityMemberServiceImpl implements CommunityMemberService{

    @Resource
    private CommunityMemberMapper communityMemberMapper;

    @Override
    public int deleteByPrimaryKey(Integer communityMemberId) {
        return communityMemberMapper.deleteByPrimaryKey(communityMemberId);
    }

    @Override
    public int insert(CommunityMember record) {
        return communityMemberMapper.insert(record);
    }

    @Override
    public int insertSelective(CommunityMember record) {
        return communityMemberMapper.insertSelective(record);
    }

    @Override
    public CommunityMember selectByPrimaryKey(Integer communityMemberId) {
        return communityMemberMapper.selectByPrimaryKey(communityMemberId);
    }

    @Override
    public int updateByPrimaryKeySelective(CommunityMember record) {
        return communityMemberMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CommunityMember record) {
        return communityMemberMapper.updateByPrimaryKey(record);
    }


    @Override
    public boolean isExistByUserIdAndCommunityId(String userId, Integer communityId) {
        return communityMemberMapper.findByCommunityIdAndUserId(communityId, userId) != null;
    }

    @Override
    public PageInfo<CommunityMember> getCommunitiesByUserIdWithPaginator(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(communityMemberMapper.findByUserIdOrderByCreatedAt(userId));
    }

}
