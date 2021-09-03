package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.BindUtil;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.dto.CollectionDto;
import cn.tju.minivideo.dto.LikeMapDto;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import cn.tju.minivideo.entity.LikeMap;
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.service.CommentService;
import cn.tju.minivideo.service.LikeMapService;
import cn.tju.minivideo.service.VideoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
//                || (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnActivity))
//                || (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnBulletScreen))
//                || (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnGoods))
        ) {
            throw new ControllerException(MsgEnums.ITEM_NOT_EXIST);
        }
        if (likeMapService.isExistByFromIdAndToIdAndLikeType(user.getUserId(), likeMap.getToId(), likeMap.getLikeType())) {
            throw new ControllerException(MsgEnums.RELATION_HAS_EXIST);
        }
        likeMapService.insertSelective(likeMap);
        if (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnVideo)) {
            videoService.addVideoLikeNumByVideoId(likeMap.getToId());
        } else if (likeMapDto.getLikeType().equals(Constants.LikeConst.LikeOnComment)) {
            commentService.addCommentLikeNumByCommentId(likeMap.getToId());
        }
        return Results.Ok();
    }

    // 收藏同样也是包含了所有类型的收藏了，按收藏类型区分
    @PostMapping("collect")
    @ApiOperation("收藏")
    public Result collectItem(@RequestBody @Validated(ValidationGroups.Insert.class) CollectionDto collectionDto) {
        return Results.Ok();
    }

    @GetMapping("video_collections")
    @AuthRequired
    @ApiOperation("获取用户的收藏")
    public Result getCollections(){
        return Results.Ok();
    }
}
