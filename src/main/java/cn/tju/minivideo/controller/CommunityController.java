package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.constants.ProjectConstant;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.*;
import cn.tju.minivideo.dto.ActivityDto;
import cn.tju.minivideo.dto.CommunityDto;
import cn.tju.minivideo.dto.LabelDto;
import cn.tju.minivideo.dto.TopicDto;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import cn.tju.minivideo.entity.*;
import cn.tju.minivideo.service.*;
import com.github.pagehelper.PageInfo;
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

    @Autowired
    private ActivityService activityService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ActivityTopicService activityTopicService;

    @Autowired
    private HistoryService historyService;

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


    @PostMapping("update_community")
    @ApiOperation("更新社区信息")
    @AuthRequired
    public Result updateCommunityInfo(@RequestBody @Validated(ValidationGroups.Update.class) CommunityDto communityDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
        Community community = communityService.getCommunityByCommunityId(communityDto.getCommunityId());
        Label mainLabel = labelService.getLabelByLabelId(community.getMainLabelId());
        log.info(mainLabel.toString());
        if (mainLabel.getLabelType().equals(Constants.LabelConst.LabelUpMasterLabelType) && (communityDto.getCommunityName() != null && !communityDto.getCommunityName().isEmpty())) {
            throw new ControllerException(MsgEnums.COMMUNITY_CANT_MODIFY_COMMUNITY_NAME);
        }
        if (communityDto.getAvatar() != null && !communityDto.getAvatar().isEmpty()) {
            mediaService.isExistByMediaUrlAndTrueFile(communityDto.getAvatar());
        }
        community = modelMapper.map(communityDto, Community.class);
        communityService.updateByPrimaryKeySelective(community);
        return Results.Ok();
    }

    @PostMapping("join_community")
    @ApiOperation("加入社区")
    @AuthRequired
    @Transactional
    public Result joinInCommunity(@RequestBody @Validated(ValidationGroups.IdForm.class) CommunityDto communityDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
        String userId = JwtInterceptor.getUser().getUserId();
        if (!communityMemberService.isExistByUserIdAndCommunityId(userId, communityDto.getCommunityId())) {
            throw new ControllerException(MsgEnums.COMMUNITY_HAS_JOIN_COMMUNITY);
        }
        CommunityMember communityMember = new CommunityMember(userId, communityDto.getCommunityId(),
                Constants.CommunityMemberConst.CommunityMemberStatusNormal,
                Constants.CommunityMemberConst.CommunityMemberNormalAuthority);
        communityMemberService.insertSelective(communityMember);
        communityService.lockCommunityByCommunityId(communityDto.getCommunityId());
        communityService.addCommunityMemberNumByCommunityId(communityDto.getCommunityId());
        return Results.Ok();
    }

    @GetMapping("is_join_community")
    @ApiOperation("查看是否已经加入社区")
    @AuthRequired
    public Result isJoinCommunity(@RequestParam(value = "communityId", defaultValue = "-1") Integer communityId) {
        if (communityId.equals(-1)) {
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        String userId = JwtInterceptor.getUser().getUserId();
        return Results.OkWithData(communityMemberService.isExistByUserIdAndCommunityId(userId, communityId));
    }


    @GetMapping("join_communities")
    @ApiOperation("获取已经加入的社区的列表")
    @AuthRequired
    public Result getHasJoinedCommunities(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        String userId = JwtInterceptor.getUser().getUserId();
        List<CommunityMember> communityMembers = communityMemberService.getCommunitiesByUserId(userId);
        List<CommunityDto> data = new ArrayList<>();
        for (CommunityMember communityMember : communityMembers) {
            CommunityDto communityDto = communityService.getCommunityDtoByCommunityId(communityMember.getCommunityId());
            data.add(communityDto);
        }
        return Results.OkWithData(data);

//        PageInfo<CommunityMember> pageInfo = communityMemberService.getCommunitiesByUserIdWithPaginator(userId, page, ProjectConstant.PageSize);
//        List<CommunityDto> data = new ArrayList<>();
//        for (CommunityMember communityMember : pageInfo.getList()) {
////            Community community = communityService.getCommunityByCommunityId(communityMember.getCommunityId());
//            CommunityDto communityDto = communityService.getCommunityDtoByCommunityId(communityMember.getCommunityId());
//            data.add(communityDto);
//        }
//
//        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }

    @GetMapping("communities")
    @ApiOperation("获取所有社区，按时间排序，暂时当作一些需要社区数据的数据提供接口")
    public Result getCommunities(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        List<Community> communities = communityService.getCommunitiesOrderByCreatedAt();
        List<CommunityDto> data = new ArrayList<>();
        for (Community community : communities) {
            data.add(communityService.getCommunityDtoByCommunity(community));
        }
        return Results.OkWithData(data);
//        PageInfo<Community> pageInfo = communityService.getCommunitiesWithPaginatorOrderByCreatedAt(page, ProjectConstant.PageSize);
//        List<CommunityDto> data = new ArrayList<>();
//        for (Community community : pageInfo.getList()) {
//            CommunityDto communityDto = communityService.getCommunityDtoByCommunity(community);
//            data.add(communityDto);
//        }
//        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }


    // activity





    @PostMapping("add_activity")
    @ApiOperation("添加帖子")
    @AuthRequired
    @Transactional
    public Result addActivity(@RequestBody @Validated(ValidationGroups.Insert.class) ActivityDto activityDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
        String userId = JwtInterceptor.getUser().getUserId();
        if (!communityMemberService.isExistByUserIdAndCommunityId(userId, activityDto.getCommunityId())) {
            throw new ControllerException(MsgEnums.PERMISSION_ERROR);
        }
        Activity activity = modelMapper.map(activityDto, Activity.class);
        activity.setUserId(userId);
//        List<String> topics = StringUtil.getTopicList(activityDto.getContent());
//        List<Integer> topicIds = topicService.getTopicIdsOrInsert(topics, userId);
//        String ids = JsonUtil.List2String(topicIds);
//        activity.setTopicIds(ids);
        activityService.insertSelective(activity);
//        activityTopicService.insertActivityTopicByTopicIdsAndActivityId(topicIds, activity.getActivityId());
        communityService.lockCommunityByCommunityId(activity.getCommunityId());
        communityService.addCommunityActivityNumByCommunityId(activity.getCommunityId());
        return Results.OkWithData(activity.getActivityId());
    }

    @PostMapping("activity_essence")
    @ApiOperation("社区管理员给帖子加精")
    @AuthRequired
    public Result activityAddEssence(@RequestBody @Validated(ValidationGroups.IdForm.class) ActivityDto activityDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
        String userId = JwtInterceptor.getUser().getUserId();
        Activity activity = activityService.getActivityByActivityId(activityDto.getActivityId());
        if(!communityMemberService.isExistByUserIdAndCommunityId(userId, activity.getCommunityId())){
            throw new ControllerException(MsgEnums.PERMISSION_ERROR);
        }
        activity.setIsEssence(Constants.ActivityConst.ActivityIsEssence);
        activityService.updateByPrimaryKeySelective(activity);
        return Results.Ok();
    }


    @GetMapping("activity_simple")
    @ApiOperation("获取帖子简易")
    public Result getActivity(@RequestParam(value = "activityId", defaultValue = "-1") Integer activityId) {
        if (activityId.equals(-1)) {
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        Activity activity = activityService.getActivityByActivityId(activityId);
        ActivityDto activityDto = modelMapper.map(activity, ActivityDto.class);
        return Results.OkWithData(activityDto);
    }

    // TODO: 增加历史
    @GetMapping("activity_detail")
    @ApiOperation("获取帖子详情")
    @AuthRequired(required = false)
    public Result getActivityDetail(@RequestParam(value = "activityId", defaultValue = "-1") Integer activityId) {
        if (activityId.equals(-1)) {
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        Activity activity = activityService.getActivityByActivityId(activityId);
        ActivityDto activityDto = modelMapper.map(activity, ActivityDto.class);
        if (JwtInterceptor.getUser() != null) {
            historyService.addHistory(JwtInterceptor.getUser().getUserId(), activityId, Constants.HistoryConst.HistoryActivityType);
        }
        return Results.OkWithData(activityDto);
    }

    @GetMapping("community_activities")
    @ApiOperation("获取社区中的帖子")
    public Result getCommunityActivities(@RequestParam(value = "communityId", defaultValue = "-1") Integer communityId,
                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "sort", defaultValue = "1") Integer sortMethod,
                                         @RequestParam(value = "essence", defaultValue = "-1") Integer isEssence) {
        if (communityId.equals(-1)
                || (!isEssence.equals(Constants.ActivityConst.ActivityNotEssence) && !isEssence.equals(Constants.ActivityConst.ActivityIsEssence)) && !isEssence.equals(Constants.ActivityConst.ActivityAnyEssence)) {
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        return Results.OkWithData(activityService.getCommunityActivitiesByEssenceSortByMethod(communityId, isEssence, sortMethod));
//        PageInfo<Activity> pageInfo = activityService.getCommunityActivitiesByEssenceWithPaginatorSortByMethod(communityId, isEssence, page, ProjectConstant.PageSize, sortMethod);
//        List<ActivityDto> data = new ArrayList<>();
//        pageInfo.getList().forEach(activity -> data.add(modelMapper.map(activity, ActivityDto.class)));
//        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }



    @GetMapping("my_activities")
    @ApiOperation("获取我发布的帖子")
    @AuthRequired
    public Result getMyActivities(@RequestParam(value = "sort", defaultValue = "1") Integer sortMethod){
        String userId = JwtInterceptor.getUser().getUserId();
        return Results.OkWithData(activityService.getActivitiesByUserIdSortByMethod(userId, sortMethod));
    }

    @GetMapping("activities_by_topic")
    @ApiOperation("根据话题获取相应的帖子，分页")
    public Result getActivitiesByTopic(@RequestParam(value = "topicName", defaultValue = "") String topicName,
                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "sort", defaultValue = "1") Integer sortMethod){
        if(topicName.equals("")){
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        Topic topic = topicService.getTopicByTopicName(topicName);
        PageInfo<Activity> pageInfo = activityService.getActivitiesByTopicIdWithPaginatorSortByMethod(topic.getTopicId(), sortMethod, page, ProjectConstant.PageSize);
        List<ActivityDto> data = new ArrayList<>();
        pageInfo.getList().forEach(activity -> data.add(modelMapper.map(activity, ActivityDto.class)));
        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }

    @GetMapping("activity_main_tmp")
    @ApiOperation("暂时的帖子首页，按顺序返回帖子，分页")
    public Result getActivitiesByPage(@RequestParam(value = "page", defaultValue = "1") Integer page) {
//        PageInfo<Activity> pageInfo = activityService.getActivitiesWithPaginator(page, ProjectConstant.PageSize);
        List<Activity> activities = activityService.getActivities();
        List<ActivityDto> data = new ArrayList<>();
//        pageInfo.getList().forEach(activity -> data.add(modelMapper.map(activity, ActivityDto.class)));
        activities.forEach(activity -> data.add(modelMapper.map(activity, ActivityDto.class)));
//        return Results.OkWithData(Paginators.paginator(pageInfo, data));
        return Results.OkWithData(data);
    }

    @GetMapping("hot_topics")
    @ApiOperation("获取热门帖子，返回5个topic")
    public Result getHotTopics(){
        List<Topic> topics = topicService.getHotTopic();
        List<TopicDto> data = new ArrayList<>();
        topics.forEach(topic -> data.add(modelMapper.map(topic, TopicDto.class)));
        return Results.OkWithData(data);
    }
}

