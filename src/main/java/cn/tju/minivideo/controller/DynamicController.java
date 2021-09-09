package cn.tju.minivideo.controller;


import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.ProjectConstant;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.BindUtil;
import cn.tju.minivideo.core.util.Paginators;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.dto.DynamicDto;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import cn.tju.minivideo.entity.Dynamic;
import cn.tju.minivideo.service.DynamicService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("dynamic")
@RestController
@Slf4j
public class DynamicController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DynamicService dynamicService;


    @PostMapping("create_dynamic")
    @ApiOperation("创建动态")
    @AuthRequired
    public Result createDynamic(@RequestBody @Validated(ValidationGroups.Insert.class)DynamicDto dynamicDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
        String userId = JwtInterceptor.getUser().getUserId();
        Dynamic dynamic = modelMapper.map(dynamicDto, Dynamic.class);
        dynamic.setDynamicType(Constants.DynamicConst.NormalDynamicType);
        dynamic.setUserId(userId);
        dynamicService.insertSelective(dynamic);
        return Results.OkWithData(dynamic.getDynamicId());
    }


    @GetMapping("get_follow_dynamics")
    @ApiOperation("获取关注的人的最新动态，分页，按时间desc排序")
    @AuthRequired
    public Result getFollowUserDynamics(@RequestParam(value = "page", defaultValue = "1") Integer page){
        String userId = JwtInterceptor.getUser().getUserId();
        PageInfo<Dynamic> pageInfo = dynamicService.getDynamicsWhereUserIsFollowedWithPaginator(userId, page, ProjectConstant.PageSize);
        List<DynamicDto> data = new ArrayList<>();
        pageInfo.getList().forEach(dynamic -> data.add(modelMapper.map(dynamic, DynamicDto.class)));
        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }
}
