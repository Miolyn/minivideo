package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.constants.ProjectConstant;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.util.Paginators;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.dto.SimpleVideoDto;
import cn.tju.minivideo.dto.UserDto;
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.entity.Video;
import cn.tju.minivideo.service.UserService;
import cn.tju.minivideo.service.VideoService;
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

@RequestMapping("search")
@Slf4j
@RestController
public class SearchController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VideoService videoService;

    @Autowired
    private UserService userService;

    @GetMapping("video")
    @ApiOperation("根据关键词和搜索方法搜索视频")
    public Result searchVideo(@RequestParam(value = "key", defaultValue = "") String key,
                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "sort", defaultValue = "1") Integer sortMethod){
        if(key.equals("")){
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        PageInfo<Video> pageInfo = videoService.searchVideoByKeyOnTitleAndIntroductionWithPaginator(key, sortMethod, page, ProjectConstant.PageSize);
        List<SimpleVideoDto> data = new ArrayList<>();
        pageInfo.getList().forEach(video -> data.add(modelMapper.map(video, SimpleVideoDto.class)));
        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }

    @GetMapping("user")
    @ApiOperation("根据关键词搜索用户")
    public Result searchUser(@RequestParam(value = "key", defaultValue = "") String key,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "sort", defaultValue = "1") Integer sortMethod){
        if(key.equals("")){
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        PageInfo<User> pageInfo = userService.searchUserByKeyOnUsernameWithPaginator(key, sortMethod, page, ProjectConstant.PageSize);
        List<UserDto> data = new ArrayList<>();
        pageInfo.getList().forEach(user -> data.add(modelMapper.map(user, UserDto.class)));
        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }
}
