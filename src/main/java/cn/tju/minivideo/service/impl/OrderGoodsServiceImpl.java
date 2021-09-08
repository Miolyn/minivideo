package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.entity.Order;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.OrderGoodsMapper;
import cn.tju.minivideo.entity.OrderGoods;
import cn.tju.minivideo.service.OrderGoodsService;

import java.util.List;

@Service
public class OrderGoodsServiceImpl implements OrderGoodsService{

    @Resource
    private OrderGoodsMapper orderGoodsMapper;

    @Override
    public int deleteByPrimaryKey(Integer orderGoodsId) {
        return orderGoodsMapper.deleteByPrimaryKey(orderGoodsId);
    }

    @Override
    public int insert(OrderGoods record) {
        return orderGoodsMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderGoods record) {
        return orderGoodsMapper.insertSelective(record);
    }

    @Override
    public OrderGoods selectByPrimaryKey(Integer orderGoodsId) {
        return orderGoodsMapper.selectByPrimaryKey(orderGoodsId);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderGoods record) {
        return orderGoodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderGoods record) {
        return orderGoodsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<OrderGoods> getOrderGoodsByOrderId(Integer orderId) {

        return orderGoodsMapper.findByOrderId(orderId);
    }

}
