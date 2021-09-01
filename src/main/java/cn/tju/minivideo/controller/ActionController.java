package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.dto.CollectionDto;
import cn.tju.minivideo.dto.LikeMapDto;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("action")
@Slf4j
public class ActionController {

    // 这个点赞接口就包括了所有类型的点赞了，通过likeType区分
    @PostMapping("like")
    @ApiOperation("点赞")
    public Result likeMap(@RequestBody @Validated({ValidationGroups.Insert.class}) LikeMapDto likeMapDto){
        return Results.Ok();
    }

    @PostMapping("collect")
    @ApiOperation("收藏")
    public Result collectItem(@RequestBody @Validated(ValidationGroups.Insert.class)CollectionDto collectionDto){
        return Results.Ok();
    }
}
