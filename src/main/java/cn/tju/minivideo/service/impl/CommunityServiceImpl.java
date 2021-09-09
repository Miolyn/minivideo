package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.CommunityMapper;
import cn.tju.minivideo.entity.Community;
import cn.tju.minivideo.service.CommunityService;
@Service
public class CommunityServiceImpl implements CommunityService{

    @Resource
    private CommunityMapper communityMapper;

    @Override
    public int deleteByPrimaryKey(Integer communityId) {
        return communityMapper.deleteByPrimaryKey(communityId);
    }

    @Override
    public int insert(Community record) {
        return communityMapper.insert(record);
    }

    @Override
    public int insertSelective(Community record) {
        return communityMapper.insertSelective(record);
    }

    @Override
    public Community selectByPrimaryKey(Integer communityId) {
        return communityMapper.selectByPrimaryKey(communityId);
    }

    @Override
    public int updateByPrimaryKeySelective(Community record) {
        return communityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Community record) {
        return communityMapper.updateByPrimaryKey(record);
    }

    @Override
    public Community getCommunityByCommunityId(Integer communityId) {
        Community community = communityMapper.findByCommunityId(communityId);
        if (community == null){
            throw new ServiceException(MsgEnums.ITEM_NOT_EXIST);
        }
        return community;
    }

}
