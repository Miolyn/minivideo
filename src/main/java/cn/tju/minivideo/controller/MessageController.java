package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.ProjectConstant;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.JsonUtil;
import cn.tju.minivideo.core.util.Paginators;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.dto.MessageDto;
import cn.tju.minivideo.dto.MsgDto;
import cn.tju.minivideo.entity.Message;
import cn.tju.minivideo.service.MessageService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("message")
@RestController
@Slf4j
public class MessageController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageService messageService;

    @GetMapping("comment_message")
    @ApiOperation("获取回复我的消息")
    @AuthRequired
    public Result getCommentSystemMessage(@RequestParam(value = "page", defaultValue = "1") Integer page){
        String userId = JwtInterceptor.getUser().getUserId();
        PageInfo<Message> pageInfo = messageService.getMessagesByToIdAndMessageTypeWithPaginator(userId, Constants.MessageConst.MessageTypeCommentNotify, page, ProjectConstant.PageSize);
        List<MessageDto> data = new ArrayList<>();
        for (Message message : pageInfo.getList()) {
            MessageDto messageDto = modelMapper.map(message, MessageDto.class);
            messageDto.setContent(JsonUtil.String2Object(message.getContent(), MsgDto.class));
            data.add(messageDto);
        }
        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }

    @GetMapping("like_message")
    @ApiOperation("获取回复我的消息")
    @AuthRequired
    public Result getLikeSystemMessage(@RequestParam(value = "page", defaultValue = "1") Integer page){
        String userId = JwtInterceptor.getUser().getUserId();
        PageInfo<Message> pageInfo = messageService.getMessagesByToIdAndMessageTypeWithPaginator(userId, Constants.MessageConst.MessageLikeNotify, page, ProjectConstant.PageSize);
        List<MessageDto> data = new ArrayList<>();
        for (Message message : pageInfo.getList()) {
            MessageDto messageDto = modelMapper.map(message, MessageDto.class);
            messageDto.setContent(JsonUtil.String2Object(message.getContent(), MsgDto.class));
            data.add(messageDto);
        }
        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }

}
