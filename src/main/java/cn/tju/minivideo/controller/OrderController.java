package cn.tju.minivideo.controller;


import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.BindUtil;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.dto.OrderDto;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import cn.tju.minivideo.entity.Goods;
import cn.tju.minivideo.entity.Order;
import cn.tju.minivideo.entity.OrderGoods;
import cn.tju.minivideo.service.AddressService;
import cn.tju.minivideo.service.GoodsService;
import cn.tju.minivideo.service.OrderGoodsService;
import cn.tju.minivideo.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("order")
public class OrderController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderGoodsService orderGoodsService;

    // TODO: add number
    @PostMapping("buy_goods")
    @ApiOperation("购买商品")
    @AuthRequired
    @Transactional
    public Result buyGoods(@RequestBody @Validated(ValidationGroups.Insert.class) OrderDto orderDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
        if(!addressService.isExistByAddressId(orderDto.getAddressId())){
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        Order order = modelMapper.map(orderDto, Order.class);
        Goods goods = goodsService.getGoodsByGoodsId(orderDto.getGoodsId());
        String userId = JwtInterceptor.getUser().getUserId();
        order.setPayPrice(goods.getPrice().multiply(new BigDecimal(orderDto.getNumber())));
        order.setStatus(Constants.OrderConst.OrderWaitForPayStatus);
        order.setUserId(userId);
        orderService.insertSelective(order);
        OrderGoods orderGoods = new OrderGoods(order.getOrderId(),orderDto.getGoodsId(), order.getPayPrice(), orderDto.getNumber(), Constants.OrderGoodsConst.OrderGoodsNormalStatus);
        orderGoodsService.insertSelective(orderGoods);
        return Results.OkWithData(order.getOrderId());
    }

    @PostMapping("pay")
    @ApiOperation("支付")
    @AuthRequired
    public Result payOrder(@RequestBody @Validated(ValidationGroups.IdForm.class) OrderDto orderDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
        Order order = orderService.getOrderByOrderId(orderDto.getOrderId());
        if(!order.getStatus().equals(Constants.OrderConst.OrderWaitForPayStatus)){
            throw new ControllerException(MsgEnums.ORDER_STATUS_ERROR);
        }
//        order.setStatus(Constants.OrderConst.OrderWaitForShipStatus);
        order.setStatus(Constants.OrderConst.OrderWaitForConfirmReceiptStatus);
        orderService.updateByPrimaryKeySelective(order);
        return Results.Ok();
    }
    // TODO: 增加销量
    @PostMapping("complete")
    @ApiOperation("确认收货")
    @AuthRequired
    @Transactional
    public Result completeOrder(@RequestBody @Validated(ValidationGroups.IdForm.class) OrderDto orderDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
        Order order = orderService.getOrderByOrderId(orderDto.getOrderId());
        if(!order.getStatus().equals(Constants.OrderConst.OrderWaitForConfirmReceiptStatus)){
            throw new ControllerException(MsgEnums.ORDER_STATUS_ERROR);
        }
//        order.setStatus(Constants.OrderConst.OrderWaitForShipStatus);
        order.setStatus(Constants.OrderConst.OrderComplete);
        orderService.updateByPrimaryKeySelective(order);
        List<OrderGoods> orderGoods = orderGoodsService.getOrderGoodsByOrderId(order.getOrderId());
        orderGoods.forEach(orderGoods1 -> {
            goodsService.lockGoodsByGoodsId(orderGoods1.getGoodsId());
            goodsService.addGoodsSaleNum(orderGoods1.getGoodsId(), orderGoods1.getNumber());
        });
        return Results.Ok();
    }
}
