package cn.tju.minivideo.core.constants;

import com.google.common.base.Charsets;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String APP_NAME = "miniVideo";

    public static final Charset CHARSET = Charsets.UTF_8;


    public interface Flag {
        int YES = 1;
        int NO = 2;
    }

    // user
    public interface UserConst {
        // status 1正常 2封号
        int StatusNormal = 1;
        int StatusBanned = 2;
        int SexMale = 1;
        int SexFemale = 2;

    }

    public interface RelationConst {
        int FollowRelation = 1;

    }

    public interface UploadConst {
        int UploadImgType = 1;
        int UploadVideoType = 2;
    }

    public interface VideoConst {
        int VideoFoodType = 1;
    }

    public interface DynamicConst {
        int NormalDynamicType = 1;
        int AutoDynamicType = 2;
    }

    public interface CommentConst {
        int CommentOnVideo = 1;
        int CommentOnComment = 2;
        int CommentOnActivity = 3;
    }

    // 1点赞视频 2点赞帖子 3点赞弹幕 4点赞商品 5点赞评论
    public interface LikeConst {
        int LikeOnVideo = 1;
        int LikeOnActivity = 2;
        int LikeOnBulletScreen = 3;
        int LikeOnGoods = 4;
        int LikeOnComment = 5;
    }

    public interface CollectionConst {
        int CollectOnVideo = 1;
        int CollectOnActivity = 2;
    }

    // 商品
    public interface GoodsConst {
        // 商品类型
        int GoodsFoodType = 1; // 食品类

    }


    // 1待付款，2待发货，3等待确认收货, 4订单完成， 5订单取消
    public interface OrderConst{
        int OrderWaitForPayStatus = 1;
        int OrderWaitForShipStatus = 2;
        int OrderWaitForConfirmReceiptStatus = 3;
        int OrderComplete = 4;
        int OrderCancel = 5;
    }

    public interface OrderGoodsConst{
        int OrderGoodsNormalStatus = 1;
    }

    public interface LabelConst {
        int LabelUpMasterLabelType = 1;
        int LabelVideoLabelType = 2;
        int LabelSystemLabelType = 3;
        int LabelSelfDesignLabelType = 4;
    }

    public interface CommunityMemberConst{
        int CommunityMemberStatusNormal = 1;
        int CommunityMemberAdministratorAuthority = 1;
        int CommunityMemberNormalAuthority = 2;
    }

    public static Map<Integer, String> goodsTypeMap = new HashMap<>();

    static {
        Field[] fields = Constants.GoodsConst.class.getFields();
        for (Field f : fields) {
            try {
                goodsTypeMap.put(f.getInt(f.getName()), f.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean isGoodsTypeValid(Integer goodsType){
        return goodsTypeMap.containsKey(goodsType);
    }

}
