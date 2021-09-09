package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Order;
import cn.tju.minivideo.entity.OrderGoods;

import java.util.List;

public interface OrderGoodsService{


    int deleteByPrimaryKey(Integer orderGoodsId);

    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);

    OrderGoods selectByPrimaryKey(Integer orderGoodsId);

    int updateByPrimaryKeySelective(OrderGoods record);

    int updateByPrimaryKey(OrderGoods record);

    List<OrderGoods> getOrderGoodsByOrderId(Integer orderId);

}
