package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> findByUserId(@Param("userId") String userId);

    List<Goods> findByUserIdOrderByCreatedAt(@Param("userId") String userId);

    List<Goods> findByUserIdOrderByLikeNum(@Param("userId") String userId);

    List<Goods> findByUserIdAndGoodsTypeOrderByCreatedAt(@Param("userId") String userId, @Param("goodsType") Integer goodsType);

    List<Goods> findByUserIdAndGoodsTypeOrderByLikeNum(@Param("userId") String userId, @Param("goodsType") Integer goodsType);


    Goods findByUserIdAndGoodsId(@Param("userId") String userId, @Param("goodsId") Integer goodsId);


}