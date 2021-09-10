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
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private DynamicService dynamicService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MessageService messageService;

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
        addCommentNumAndSystemMessage(comment);
        commentService.insertSelective(comment);
        return Results.OkWithData(comment.getCommentId());
    }

    private void addCommentNumAndSystemMessage(Comment comment1){
        if (comment1.getCommentType().equals(Constants.CommentConst.CommentOnVideo)){
            videoService.lockVideoByVideoId(comment1.getToId());
            videoService.addVideoCommentNumByVideoId(comment1.getToId());
            messageService.addSystemNotifyMessage(videoService.getUserIdOfrVideoByVideoId(comment1.getToId()),
                    Constants.MessageConst.MessageTypeCommentNotify,
                    messageService.getMsgDtoByObject(comment1, Constants.MessageConst.ItemTypeVideo, Constants.MessageConst.MessageTypeCommentNotify));
        } else if(comment1.getCommentType().equals(Constants.CommentConst.CommentOnActivity)){
            activityService.lockActivityByActivityId(comment1.getToId());
            activityService.addCommentNumByActivityId(comment1.getToId());
            messageService.addSystemNotifyMessage(activityService.getUserIdOfActivityByActivityId(comment1.getToId()),
                    Constants.MessageConst.MessageTypeCommentNotify,
                    messageService.getMsgDtoByObject(comment1, Constants.MessageConst.ItemTypeActivity, Constants.MessageConst.MessageTypeCommentNotify));
        } else if (comment1.getCommentType().equals(Constants.CommentConst.CommentOnComment)){
            Comment comment = commentService.getCommentByCommentId(comment1.getToId());
            messageService.addSystemNotifyMessage(comment.getFromId(),
                    Constants.MessageConst.MessageTypeCommentNotify,
                    messageService.getMsgDtoByObject(comment1, Constants.MessageConst.ItemTypeComment, Constants.MessageConst.MessageTypeCommentNotify));
//            CommentDto commentDto1 = modelMapper.map(comment, CommentDto.class);
            addCommentNumAndSystemMessage(comment);
        } else if (comment1.getCommentType().equals(Constants.CommentConst.CommentOnGoods)){
            if(!orderService.checkPermissionToCommentOnGoods(comment1.getToId(), comment1.getFromId())){
                throw new ControllerException(MsgEnums.PERMISSION_ERROR);
            }
            goodsService.lockGoodsByGoodsId(comment1.getToId());
            goodsService.addGoodsCommentNumByGoodsId(comment1.getToId());
            messageService.addSystemNotifyMessage(goodsService.getUserIdOfGoodsByGoodsId(comment1.getToId()),
                    Constants.MessageConst.MessageTypeCommentNotify,
                    messageService.getMsgDtoByObject(comment1, Constants.MessageConst.ItemTypeGoods, Constants.MessageConst.MessageTypeCommentNotify));
        } else if (comment1.getCommentType().equals(Constants.CommentConst.CommentOnDynamic)){
            dynamicService.lockDynamicByDynamicId(comment1.getToId());
            dynamicService.addDynamicCommentNumByDynamicId(comment1.getToId());
            messageService.addSystemNotifyMessage(dynamicService.getUserIdOfDynamicByDynamicId(comment1.getToId()),
                    Constants.MessageConst.MessageTypeCommentNotify,
                    messageService.getMsgDtoByObject(comment1, Constants.MessageConst.ItemTypeDynamic, Constants.MessageConst.MessageTypeCommentNotify));
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
