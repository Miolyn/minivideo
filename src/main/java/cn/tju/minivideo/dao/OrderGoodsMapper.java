package cn.tju.minivideo.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.tju.minivideo.entity.OrderGoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderGoodsMapper {
    int deleteByPrimaryKey(Integer orderGoodsId);

    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);

    OrderGoods selectByPrimaryKey(Integer orderGoodsId);

    int updateByPrimaryKeySelective(OrderGoods record);

    int updateByPrimaryKey(OrderGoods record);

    List<OrderGoods> findByOrderId(@Param("orderId")Integer orderId);


}