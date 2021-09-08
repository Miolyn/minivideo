package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.constants.ProjectConstant;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.BindUtil;
import cn.tju.minivideo.core.util.JsonUtil;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.dto.CommunityDto;
import cn.tju.minivideo.dto.LabelDto;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import cn.tju.minivideo.entity.Community;
import cn.tju.minivideo.entity.CommunityMember;
import cn.tju.minivideo.entity.Label;
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.service.CommunityMemberService;
import cn.tju.minivideo.service.CommunityService;
import cn.tju.minivideo.service.LabelService;
import cn.tju.minivideo.service.MediaService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("community")
@RestController
@Slf4j
public class CommunityController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private CommunityMemberService communityMemberService;

    @Autowired
    private MediaService mediaService;

    @PostMapping("create_community")
    @ApiOperation("创建社区")
    @AuthRequired
    @Transactional
    public Result createCommunity(@RequestBody @Validated(ValidationGroups.Insert.class) CommunityDto communityDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
        User user = JwtInterceptor.getUser();
        // media
        mediaService.isExistByMediaUrlAndTrueFile(communityDto.getAvatar());
        // label
        if (communityDto.getMainLabel().getLabelId() == null && communityDto.getMainLabel().getLabelType().equals(Constants.LabelConst.LabelUpMasterLabelType)) {
            Label label = modelMapper.map(communityDto.getMainLabel(), Label.class);
            label.setLabelName(user.getUsername() + ProjectConstant.LabelUpMasterSuffix);
            communityDto.setCommunityName(user.getUsername() + ProjectConstant.LabelUpMasterSuffix);
            if (labelService.isExistByLabelNameAndLabelType(label.getLabelName(), label.getLabelType())) {
                throw new ControllerException(MsgEnums.COMMUNITY_HAS_EXIST);
            }
            label.setLabelId(null);
            labelService.insertSelective(label);
            communityDto.setMainLabelId(label.getLabelId());
        } else if (!labelService.isExistByLabelId(communityDto.getMainLabel().getLabelId())) {
            throw new ControllerException(MsgEnums.COMMUNITY_LABEL_NOT_EXIT);
        } else {
            communityDto.setMainLabelId(communityDto.getMainLabel().getLabelId());
        }
        if (!communityDto.getSelfLabels().isEmpty()) {
            List<Label> labels = new ArrayList<>();
            for (String selfLabel : communityDto.getSelfLabels()) {
                Label label = new Label();
                label.setLabelName(selfLabel);
                label.setLabelType(Constants.LabelConst.LabelSelfDesignLabelType);
                labels.add(label);
            }
            List<Integer> labelIds = labelService.getLabelIdOrInsert(labels);
            communityDto.setSelfLabelIds(JsonUtil.List2String(labelIds));
        } else {
            communityDto.setSelfLabelIds("");
        }
        // createCommunity
        Community community = modelMapper.map(communityDto, Community.class);
        community.setUserId(user.getUserId());
        community.setMemberNum(1);
        communityService.insertSelective(community);
        // communityMember
        CommunityMember communityMember = new CommunityMember(user.getUserId(), community.getCommunityId(),
                Constants.CommunityMemberConst.CommunityMemberStatusNormal,
                Constants.CommunityMemberConst.CommunityMemberAdministratorAuthority);
        communityMemberService.insertSelective(communityMember);
        return Results.OkWithData(community.getCommunityId());
    }


    @GetMapping("labels")
    @ApiOperation("获取视频类型标签或系统自定义标签")
    public Result getLabelsByLabelType(@RequestParam(value = "labelType", defaultValue = "-1") Integer labelType) {
        if (labelType.equals(-1) ||
                (!labelType.equals(Constants.LabelConst.LabelSystemLabelType) && !labelType.equals(Constants.LabelConst.LabelVideoLabelType))
        ) {
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        List<LabelDto> data = new ArrayList<>();
        List<Label> labels = labelService.getLabelByLabelType(labelType);
        labels.forEach(label -> data.add(modelMapper.map(label, LabelDto.class)));
        return Results.OkWithData(data);
    }

}
