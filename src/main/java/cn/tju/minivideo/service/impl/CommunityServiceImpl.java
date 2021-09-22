package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
import cn.tju.minivideo.core.util.JsonUtil;
import cn.tju.minivideo.dto.CommunityDto;
import cn.tju.minivideo.dto.LabelDto;
import cn.tju.minivideo.entity.Label;
import cn.tju.minivideo.service.LabelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.CommunityMapper;
import cn.tju.minivideo.entity.Community;
import cn.tju.minivideo.service.CommunityService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService{

    @Resource
    private CommunityMapper communityMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LabelService labelService;

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

    @Override
    public CommunityDto getCommunityDtoByCommunityId(Integer communityId) {
        Community community = communityMapper.findByCommunityId(communityId);
        if (community == null){
            throw new ServiceException(MsgEnums.ITEM_NOT_EXIST);
        }
//        CommunityDto communityDto = modelMapper.map(community, CommunityDto.class);
//        Label mainLabel = labelService.getLabelByLabelId(community.getMainLabelId());
//        communityDto.setMainLabel(modelMapper.map(mainLabel, LabelDto.class));
//        List<LabelDto> selfLabels = new ArrayList<>();
//        for (Integer id : JsonUtil.String2List(community.getSelfLabelIds(), Integer.class)) {
//            Label selfLabel = labelService.getLabelByLabelId(id);
//            selfLabels.add(modelMapper.map(selfLabel, LabelDto.class));
//        }
//        communityDto.setSelfLabelDtos(selfLabels);
        return getCommunityDtoByCommunity(community);
    }

    @Override
    public CommunityDto getCommunityDtoByCommunity(Community community) {
        CommunityDto communityDto = modelMapper.map(community, CommunityDto.class);
        Label mainLabel = labelService.getLabelByLabelId(community.getMainLabelId());
        communityDto.setMainLabel(modelMapper.map(mainLabel, LabelDto.class));
        List<LabelDto> selfLabels = new ArrayList<>();
        for (Integer id : JsonUtil.String2List(community.getSelfLabelIds(), Integer.class)) {
            Label selfLabel = labelService.getLabelByLabelId(id);
            selfLabels.add(modelMapper.map(selfLabel, LabelDto.class));
        }
        communityDto.setSelfLabelDtos(selfLabels);
        return communityDto;
    }


    @Override
    public PageInfo<Community> getCommunitiesWithPaginatorOrderByCreatedAt(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(communityMapper.findOrderByCreatedAt());
    }

    @Override
    public List<Community> getCommunitiesOrderByCreatedAt() {
        return communityMapper.findOrderByCreatedAt();
    }

    @Override
    public void lockCommunityByCommunityId(Integer communityId) {
        communityMapper.findByCommunityIdForUpdate(communityId);
    }

    @Override
    public int addCommunityActivityNumByCommunityId(Integer communityId) {
        return communityMapper.updateActivityNumByCommunityId(communityId);
    }

    @Override
    public int addCommunityMemberNumByCommunityId(Integer communityId) {
        return communityMapper.updateMemberNumByCommunityId(communityId);
    }


}
