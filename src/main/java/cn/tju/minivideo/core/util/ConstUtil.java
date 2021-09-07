package cn.tju.minivideo.core.util;

import cn.tju.minivideo.core.constants.Constants;

public class ConstUtil {
    public static boolean isGoodsTypeValid(Integer goodsType){
        return Constants.goodsTypeMap.containsKey(goodsType);
    }
}
