package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.constants.ProjectConstant;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.BindUtil;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.dto.CommentDto;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import cn.tju.minivideo.entity.Comment;
import cn.tju.minivideo.entity.OrderGoods;
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("comment")
@Slf4j
public class CommentController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderGoodsService orderGoodsService;

    @Autowired
    private OrderService orderService;

    @PostMapping("comment")
    @ApiOperation("评论某对象")
    @AuthRequired
    public Result commentOnItem(@RequestBody @Validated(ValidationGroups.Insert.class) CommentDto commentDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
        Comment comment = modelMapper.map(commentDto, Comment.class);
        User user = JwtInterceptor.getUser();
        comment.setFromId(user.getUserId());
        commentDto.setFromId(user.getUserId());
        if (   (commentDto.getCommentType().equals(Constants.CommentConst.CommentOnVideo)   && !videoService.isVideoExistByVideoId(commentDto.getToId()))
            || (commentDto.getCommentType().equals(Constants.CommentConst.CommentOnComment) && !commentService.isCommentExistByCommentId(commentDto.getToId()))
            || (commentDto.getCommentType().equals(Constants.CommentConst.CommentOnActivity) && !activityService.isActivityExistByActivityId(commentDto.getToId()))
            || (commentDto.getCommentType().equals(Constants.CommentConst.CommentOnGoods) && !goodsService.isExistGoodsByGoodsId(commentDto.getToId()))
        ){
            throw new ControllerException(MsgEnums.ITEM_NOT_EXIST);
        }
//        if (commentDto.getCommentType().equals(Constants.CommentConst.CommentOnVideo)){
//            videoService.lockVideoByVideoId(commentDto.getToId());
//            videoService.addVideoCommentNumByVideoId(commentDto.getToId());
//        } else if(commentDto.getCommentType().equals(Constants.CommentConst.CommentOnActivity)){
//            activityService.lockActivityByActivityId(commentDto.getToId());
//            activityService.addCommentNumByActivityId(commentDto.getToId());
//        }
        addCommentNum(commentDto);
        commentService.insertSelective(comment);
        return Results.OkWithData(comment.getCommentId());
    }

    private void addCommentNum(CommentDto commentDto){
        if (commentDto.getCommentType().equals(Constants.CommentConst.CommentOnVideo)){
            videoService.lockVideoByVideoId(commentDto.getToId());
            videoService.addVideoCommentNumByVideoId(commentDto.getToId());
        } else if(commentDto.getCommentType().equals(Constants.CommentConst.CommentOnActivity)){
            activityService.lockActivityByActivityId(commentDto.getToId());
            activityService.addCommentNumByActivityId(commentDto.getToId());
        } else if (commentDto.getCommentType().equals(Constants.CommentConst.CommentOnComment)){
            Comment comment = commentService.getCommentByCommentId(commentDto.getToId());
            CommentDto commentDto1 = modelMapper.map(comment, CommentDto.class);
            addCommentNum(commentDto1);
        } else if (commentDto.getCommentType().equals(Constants.CommentConst.CommentOnGoods)){
            if(!orderService.checkPermissionToCommentOnGoods(commentDto.getToId(), commentDto.getFromId())){
                throw new ControllerException(MsgEnums.PERMISSION_ERROR);
            }
            goodsService.lockGoodsByGoodsId(commentDto.getToId());
            goodsService.addGoodsCommentNumByGoodsId(commentDto.getToId());
        } else {
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
    }


    @GetMapping("comments")
    @ApiOperation("获取某对象的评论")
    public Result getComments(@RequestParam(value = "itemId", defaultValue = "-1") Integer itemId,
                              @RequestParam(value = "itemType", defaultValue = "-1") Integer itemType,
                              @RequestParam(value = "page", defaultValue = "1") Integer page){
        if (itemId.equals(-1) || itemType.equals(-1)){
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        List<CommentDto> commentDtos = commentService.getCommentsByItemIdAndItemTypeWithPaginator(itemId, itemType, page, ProjectConstant.PageSize);
        return Results.OkWithData(commentDtos);
    }
}
