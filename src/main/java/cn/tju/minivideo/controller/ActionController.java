package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.util.Results;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("action")
@Slf4j
public class ActionController {

    @PostMapping("like")
    @ApiOperation("点赞")
    public Result likeMap(){
        return Results.Ok();
    }
}
