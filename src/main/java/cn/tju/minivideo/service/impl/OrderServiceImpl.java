package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.OrderMapper;
import cn.tju.minivideo.entity.Order;
import cn.tju.minivideo.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Override
    public int deleteByPrimaryKey(Integer orderId) {
        return orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public int insert(Order record) {
        return orderMapper.insert(record);
    }

    @Override
    public int insertSelective(Order record) {
        return orderMapper.insertSelective(record);
    }

    @Override
    public Order selectByPrimaryKey(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Order record) {
        return orderMapper.updateByPrimaryKey(record);
    }

    @Override
    public Order getOrderByOrderId(Integer orderId) {
        Order order = orderMapper.findByOrderId(orderId);
        if (order == null) {
            throw new ServiceException(MsgEnums.ITEM_NOT_EXIST);
        }
        return order;
    }

    @Override
    public boolean checkPermissionToCommentOnGoods(Integer goodsId, String userId) {
        return orderMapper.countByUserIdAndGoodsId(userId, goodsId) != 0;
    }

    @Override
    public List<Order> getOrdersByUserIdAndStatus(String userId, Integer status) {
        if(status.equals(Constants.OrderConst.OrderAnyStatus)){
            return orderMapper.findByUserId(userId);
        } else{
            return orderMapper.findByUserIdAndStatus(userId, status);
        }
    }

}
