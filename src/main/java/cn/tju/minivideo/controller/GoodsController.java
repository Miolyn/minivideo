package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.constants.ProjectConstant;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.*;
import cn.tju.minivideo.dto.GoodsDto;
import cn.tju.minivideo.dto.OrderDto;
import cn.tju.minivideo.dto.SimpleGoodsDto;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import cn.tju.minivideo.entity.Goods;
import cn.tju.minivideo.entity.Order;
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.service.GoodsService;
import cn.tju.minivideo.service.HistoryService;
import cn.tju.minivideo.service.MediaService;
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

@RestController
@Slf4j
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private HistoryService historyService;

    @PostMapping("create_goods")
    @ApiOperation("上传商品")
    @AuthRequired
    public Result createGoods(@RequestBody @Validated(ValidationGroups.Insert.class) GoodsDto goodsDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
        Goods goods = modelMapper.map(goodsDto, Goods.class);
        if(!ConstUtil.isGoodsTypeValid(goodsDto.getGoodsType()) || !mediaService.isExistByMediaUrlAndTrueFile(goodsDto.getAvatar())){
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        for (String img : goodsDto.getImgs()) {
            if(!mediaService.isExistByMediaUrlAndTrueFile(img)){
                throw new ControllerException(MsgEnums.VALIDATION_ERROR);
            }
        }
//        if(!mediaService.isExistByMediaUrlAndTrueFile(goodsDto.getAvatar())){
//            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
//        }
        String imgs = JsonUtil.List2String(goodsDto.getImgs());
        goods.setImgs(imgs);
        String userId = JwtInterceptor.getUser().getUserId();
        goods.setUserId(userId);
        goodsService.insertSelective(goods);
        log.info(goods.toString());
        return Results.Ok();
    }

    @PostMapping("update_goods")
    @ApiOperation("更新商品信息")
    @AuthRequired
    public Result updateGoodsProfile(@RequestBody @Validated(ValidationGroups.Update.class) GoodsDto goodsDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
        Goods goods = modelMapper.map(goodsDto, Goods.class);
        String userId = JwtInterceptor.getUser().getUserId();
        if(!goodsService.checkPermissionToUpdateGoodsInfo(goods.getGoodsId(), userId)){
            throw new ControllerException(MsgEnums.PERMISSION_ERROR);
        }
        if(goodsDto.getImgs() != null){
            for (String img : goodsDto.getImgs()) {
                if(!mediaService.isExistByMediaUrlAndMediaTypeAndTrueFile(img, Constants.UploadConst.UploadImgType)){
                    throw new ControllerException(MsgEnums.VALIDATION_ERROR);
                }
            }
            goods.setImgs(JsonUtil.List2String(goodsDto.getImgs()));
        }
        if(goodsDto.getAvatar() != null && !mediaService.isExistByMediaUrlAndMediaTypeAndTrueFile(goodsDto.getAvatar(), Constants.UploadConst.UploadImgType)){
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        goodsService.updateByPrimaryKeySelective(goods);
        return Results.Ok();
    }

    @PostMapping("delete_goods")
    @ApiOperation("删除商品")
    @AuthRequired
    public Result deleteGoodsLogically(@RequestBody @Validated(ValidationGroups.IdForm.class) GoodsDto goodsDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
        Goods goods = modelMapper.map(goodsDto, Goods.class);
        String userId = JwtInterceptor.getUser().getUserId();
        if(!goodsService.checkPermissionToUpdateGoodsInfo(goods.getGoodsId(), userId)){
            throw new ControllerException(MsgEnums.PERMISSION_ERROR);
        }
        goodsService.deleteGoodsByGoodsIdLogically(goodsDto.getGoodsId());
        return Results.Ok();
    }

    @GetMapping("user_goods_simple")
    @ApiOperation("获取用户的商品列表")
    @AuthRequired(required = false)
    public Result getUserGoods(@RequestParam(value = "userId", defaultValue = "") String userId,
                               @RequestParam(value = "goodsType", defaultValue = "-1") Integer goodsType,
                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "sort", defaultValue = "1") Integer sortMethod){
        if(userId.equals("")){
            User user = JwtInterceptor.getUser();
            if (user == null){
                throw new ControllerException(MsgEnums.VALIDATION_ERROR);
            }
            userId = user.getUserId();
        }
        log.info(userId);
        if(!goodsType.equals(-1) &&!ConstUtil.isGoodsTypeValid(goodsType)){
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        PageInfo<Goods> pageInfo = goodsService.getGoodsByUserIdOrGoodsTypeWithPaginatorSortByMethod(userId, goodsType, page, ProjectConstant.PageSize, sortMethod);
        List<SimpleGoodsDto> data = new ArrayList<>();
        for (Goods goods : pageInfo.getList()) {
            SimpleGoodsDto simpleGoodsDto = modelMapper.map(goods, SimpleGoodsDto.class);
            data.add(simpleGoodsDto);
        }
        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }

    @GetMapping("goods_detail")
    @ApiOperation("获取商品详情")
    @AuthRequired(required = false)
    public Result getGoodsDetail(@RequestParam(value = "goodsId", defaultValue = "-1") Integer goodsId){
        if (goodsId.equals(-1)){
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        Goods goods = goodsService.getGoodsByGoodsIdWithContent(goodsId);
        log.info(goods.toString());
        GoodsDto goodsDto = modelMapper.map(goods, GoodsDto.class);
        goodsDto.setImgs(JsonUtil.String2List(goods.getImgs(), String.class));
        if(JwtInterceptor.getUser() != null){
            historyService.addHistory(JwtInterceptor.getUser().getUserId(), goodsId, Constants.HistoryConst.HistoryGoodsType);
        }
        return Results.OkWithData(goodsDto);
    }


}
