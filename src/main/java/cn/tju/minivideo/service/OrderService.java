package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Order;
public interface OrderService{


    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order getOrderByOrderId(Integer orderId);

}
