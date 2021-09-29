package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Goods;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface GoodsService {
    interface SortMethod {
        Integer SortByTimeDesc = 1;
        Integer SortByLikeNumDesc = 2;
    }

    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    PageInfo<Goods> getGoodsByUserIdOrGoodsTypeWithPaginatorSortByMethod(String userId, Integer goodsType, Integer page, Integer pageSize, Integer sortMethod);

    List<Goods> getGoodsByUserId(String userId);

    boolean checkPermissionToUpdateGoodsInfo(Integer goodsId, String userId);

    Goods getGoodsByGoodsIdWithContent(Integer goodsId);

    Goods getGoodsByGoodsId(Integer goodsId);
    Goods getGoodsByGoodsIdWithoutError(Integer goodsId);

    int addGoodsSaleNum(Integer goodsId, Integer addNum);

    int addGoodsLikeNumByGoodsId(Integer goodsId);

    int addGoodsCollectNumByGoodsId(Integer goodsId);

    int addGoodsCommentNumByGoodsId(Integer goodsId);

    void lockGoodsByGoodsId(Integer goodsId);

    boolean isExistGoodsByGoodsId(Integer goodsId);

    String getUserIdOfGoodsByGoodsId(Integer goodsId);

    int deleteGoodsByGoodsIdLogically(Integer goodsId);
}








