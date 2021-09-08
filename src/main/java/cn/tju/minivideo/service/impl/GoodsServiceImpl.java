package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.GoodsMapper;
import cn.tju.minivideo.entity.Goods;
import cn.tju.minivideo.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public int deleteByPrimaryKey(Integer goodsId) {
        return goodsMapper.deleteByPrimaryKey(goodsId);
    }

    @Override
    public int insert(Goods record) {
        return goodsMapper.insert(record);
    }

    @Override
    public int insertSelective(Goods record) {
        return goodsMapper.insertSelective(record);
    }

    @Override
    public Goods selectByPrimaryKey(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return goodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Goods record) {
        return goodsMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        return goodsMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Goods> getGoodsByUserIdOrGoodsTypeWithPaginatorSortByMethod(String userId, Integer goodsType, Integer page, Integer pageSize, Integer sortMethod) {
        PageHelper.startPage(page, pageSize);
        PageInfo<Goods> pageInfo;
        if (goodsType.equals(-1)) {
            if (sortMethod.equals(SortMethod.SortByTimeDesc)) {
                pageInfo = new PageInfo<>(goodsMapper.findByUserIdOrderByCreatedAt(userId));
            } else if (sortMethod.equals(SortMethod.SortByLikeNumDesc)) {
                pageInfo = new PageInfo<>(goodsMapper.findByUserIdOrderByLikeNum(userId));
            } else {
                throw new ServiceException(MsgEnums.ACTION_NOT_FOUND);
            }
        } else {
            if (sortMethod.equals(SortMethod.SortByTimeDesc)) {
                pageInfo = new PageInfo<>(goodsMapper.findByUserIdAndGoodsTypeOrderByCreatedAt(userId, goodsType));
            } else if (sortMethod.equals(SortMethod.SortByLikeNumDesc)) {
                pageInfo = new PageInfo<>(goodsMapper.findByUserIdAndGoodsTypeOrderByLikeNum(userId, goodsType));
            } else {
                throw new ServiceException(MsgEnums.ACTION_NOT_FOUND);
            }
        }
        return pageInfo;
    }

    @Override
    public boolean checkPermissionToUpdateGoodsInfo(Integer goodsId, String userId) {
        return goodsMapper.findByUserIdAndGoodsId(userId, goodsId) != null;
    }

    @Override
    public Goods getGoodsByGoodsIdWithContent(Integer goodsId) {
        Goods goods = goodsMapper.findByGoodsIdWithContent(goodsId);
        if (goods == null) {
            throw new ServiceException(MsgEnums.ITEM_NOT_EXIST);
        }
        return goods;
    }

    @Override
    public Goods getGoodsByGoodsId(Integer goodsId) {
        Goods goods = goodsMapper.findByGoodsId(goodsId);
        if (goods == null) {
            throw new ServiceException(MsgEnums.ITEM_NOT_EXIST);
        }
        return goods;
    }

    @Override
    public int addGoodsSaleNum(Integer goodsId, Integer addNum) {
        return goodsMapper.updateSaleNumByGoodsId(addNum, goodsId);
    }

    @Override
    public void lockGoodsByGoodsId(Integer goodsId) {
        Goods goods = goodsMapper.findByGoodsIdForUpdate(goodsId);
        if (goods == null){
            throw new ServiceException(MsgEnums.ITEM_NOT_EXIST);
        }
    }
}



