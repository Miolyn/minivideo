package cn.tju.minivideo.core.util;

import cn.tju.minivideo.core.constants.Constants;

public class ConstUtil {
    public static boolean isGoodsTypeValid(Integer goodsType){
        return Constants.goodsTypeMap.containsKey(goodsType);
    }

    public static boolean isHistoryTypeValid(Integer goodsType){
        return Constants.historyTypeMap.containsKey(goodsType);
    }

    public static boolean isVideoTypeValid(Integer goodsType){
        return Constants.videoTypeMap.containsKey(goodsType);
    }

}
