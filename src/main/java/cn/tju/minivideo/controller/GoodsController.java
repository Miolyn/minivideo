package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.util.BindUtil;
import cn.tju.minivideo.core.util.JsonUtil;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.dto.GoodsDto;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import cn.tju.minivideo.entity.Goods;
import cn.tju.minivideo.service.GoodsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GoodsService goodsService;

    @PostMapping("goods")
    @ApiOperation("上传商品")
    @AuthRequired
    public Result createGoods(@RequestBody @Validated(ValidationGroups.Insert.class) GoodsDto goodsDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
        Goods goods = modelMapper.map(goodsDto, Goods.class);

        String imgs = JsonUtil.List2String(goodsDto.getImgs());
        goods.setImgs(imgs);
        return Results.Ok();
    }
}
