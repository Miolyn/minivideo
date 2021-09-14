package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.constants.ProjectConstant;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.BindUtil;
import cn.tju.minivideo.core.util.Paginators;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.dto.CollectionDto;
import cn.tju.minivideo.dto.LikeMapDto;
import cn.tju.minivideo.dto.SimpleVideoDto;
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

@RestController
@RequestMapping("action")
@Slf4j
public class ActionController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LikeMapService likeMapService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CollectionsService collectionsService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private DynamicService dynamicService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    // 这个点赞接口就包括了所有类型的点赞了，通过likeType区分
    @PostMapping("like")
    @AuthRequired
    @ApiOperation("点赞")
    @Transactional
    public Result likeMap(@RequestBody @Validated({ValidationGroups.Insert.class}) LikeMapDto likeMapDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
        LikeMap likeMap = modelMapper.map(likeMapDto, LikeMap.class);
        User user = JwtInterceptor.getUser();
        likeMap.setFromId(user.getUserId());
        if ((likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnVideo) && !videoService.isVideoExistByVideoId(likeMapDto.getToId()))
                || (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnComment) && !commentService.isCommentExistByCommentId(likeMapDto.getToId()))
                || (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnActivity) && !activityService.isActivityExistByActivityId(likeMapDto.getToId()))
//                || (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnBulletScreen))
                || (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnGoods) && !goodsService.isExistGoodsByGoodsId(likeMapDto.getToId()))
                || (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnDynamic) && !dynamicService.isExistDynamicByDynamicId(likeMapDto.getToId()))
        ) {
            throw new ControllerException(MsgEnums.ITEM_NOT_EXIST);
        }
        if (likeMapService.isExistByFromIdAndToIdAndLikeType(user.getUserId(), likeMap.getToId(), likeMap.getLikeType())) {
            throw new ControllerException(MsgEnums.RELATION_HAS_EXIST);
        }
        likeMapService.insertSelective(likeMap);
        if (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnVideo)) {
            videoService.lockVideoByVideoId(likeMap.getToId());
            videoService.addVideoLikeNumByVideoId(likeMap.getToId());
            String userId = videoService.getUserIdOfrVideoByVideoId(likeMap.getToId());
            messageService.addSystemNotifyMessage(userId,
                    Constants.MessageConst.MessageLikeNotify,
                    messageService.getMsgDtoByObject(likeMap, Constants.MessageConst.ItemTypeVideo, Constants.MessageConst.MessageLikeNotify));
            userService.lockUserByUserId(userId);
            userService.updateUserLikeNum(userId);
        } else if (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnComment)) {
            commentService.lockCommentByCommentId(likeMap.getToId());
            commentService.addCommentLikeNumByCommentId(likeMap.getToId());
            Comment comment = commentService.getCommentByCommentId(likeMap.getToId());
            messageService.addSystemNotifyMessage(comment.getFromId(),
                    Constants.MessageConst.MessageLikeNotify,
                    messageService.getMsgDtoByObject(likeMap, Constants.MessageConst.ItemTypeComment, Constants.MessageConst.MessageLikeNotify));
        } else if (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnActivity)) {
            activityService.lockActivityByActivityId(likeMap.getToId());
            activityService.addLikeNumByActivityId(likeMap.getToId());
            messageService.addSystemNotifyMessage(activityService.getUserIdOfActivityByActivityId(likeMap.getToId()),
                    Constants.MessageConst.MessageLikeNotify,
                    messageService.getMsgDtoByObject(likeMap, Constants.MessageConst.ItemTypeActivity, Constants.MessageConst.MessageLikeNotify));
        } else if (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnGoods)) {
            goodsService.lockGoodsByGoodsId(likeMap.getToId());
            goodsService.addGoodsLikeNumByGoodsId(likeMap.getToId());
            messageService.addSystemNotifyMessage(goodsService.getUserIdOfGoodsByGoodsId(likeMap.getToId()),
                    Constants.MessageConst.MessageLikeNotify,
                    messageService.getMsgDtoByObject(likeMap, Constants.MessageConst.ItemTypeGoods, Constants.MessageConst.MessageLikeNotify));
        } else if (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnDynamic)) {
            dynamicService.lockDynamicByDynamicId(likeMap.getToId());
            dynamicService.addDynamicLikeNumByDynamicId(likeMap.getToId());
            messageService.addSystemNotifyMessage(dynamicService.getUserIdOfDynamicByDynamicId(likeMap.getToId()),
                    Constants.MessageConst.MessageLikeNotify,
                    messageService.getMsgDtoByObject(likeMap, Constants.MessageConst.ItemTypeDynamic, Constants.MessageConst.MessageLikeNotify));
        }
        return Results.Ok();
    }

    // 收藏同样也是包含了所有类型的收藏了，按收藏类型区分
    @PostMapping("collect")
    @ApiOperation("收藏")
    @AuthRequired
    public Result collectItem(@RequestBody @Validated(ValidationGroups.Insert.class) CollectionDto collectionDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
        Collections collections = modelMapper.map(collectionDto, Collections.class);
        String userId = JwtInterceptor.getUser().getUserId();
        collections.setUserId(userId);
        if ((collections.getItemType().equals(Constants.CollectionConst.CollectOnVideo) && !videoService.isVideoExistByVideoId(collections.getItemId()))
                || (collections.getItemType().equals(Constants.CollectionConst.CollectOnActivity) && !activityService.isActivityExistByActivityId(collections.getItemId()))
                || (collections.getItemType().equals(Constants.CollectionConst.CollectOnGoods) && !goodsService.isExistGoodsByGoodsId(collections.getItemId()))
        ) {
            throw new ControllerException(MsgEnums.ITEM_NOT_EXIST);
        }
        if (collectionsService.isExistByItemIdAndUserIdAnItemType(collections.getItemId(), userId, collections.getItemType())) {
            throw new ControllerException(MsgEnums.RELATION_HAS_EXIST);
        }
        collectionsService.insertSelective(collections);
        if (collections.getItemType().equals(Constants.CollectionConst.CollectOnVideo)) {
            videoService.lockVideoByVideoId(collections.getItemId());
            videoService.addVideoCollectNumByVideoId(collections.getItemId());
        } else if (collections.getItemType().equals(Constants.CollectionConst.CollectOnActivity)) {
            activityService.lockActivityByActivityId(collections.getItemId());
            activityService.addCollectNumByActivityId(collections.getItemId());
        } else if (collections.getItemType().equals(Constants.CollectionConst.CollectOnGoods)) {
            goodsService.lockGoodsByGoodsId(collections.getItemId());
            goodsService.addGoodsCollectNumByGoodsId(collections.getItemId());
        }
        return Results.Ok();
    }

    @GetMapping("collections")
    @AuthRequired
    @ApiOperation("获取用户的收藏")
    public Result getCollections(@RequestParam(value = "itemType", defaultValue = "-1") Integer itemType,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page) {
        if (itemType.equals(-1)) {
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        String userId = JwtInterceptor.getUser().getUserId();
        PageInfo<Collections> pageInfo = collectionsService.getCollectionsByItemTypeAndUserIdWithPaginator(itemType, userId, page, ProjectConstant.PageSize);
//        if (itemType.equals(Constants.CollectionConst.CollectOnVideo)) {
//            List<SimpleVideoDto> simpleVideoDtos = new ArrayList<>();
//            for (Collections collections : pageInfo.getList()) {
//                Video video = videoService.selectByPrimaryKey(collections.getItemId());
//                SimpleVideoDto simpleVideoDto = modelMapper.map(video, SimpleVideoDto.class);
//                simpleVideoDtos.add(simpleVideoDto);
//            }
//            return Results.OkWithData(Paginators.paginator(pageInfo, simpleVideoDtos));
//        }
        List<CollectionDto> data = new ArrayList<>();
        pageInfo.getList().forEach(collections -> data.add(modelMapper.map(collections, CollectionDto.class)));
        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }
}
