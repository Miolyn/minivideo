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

    public interface VideoTypeConst {
        int VideoFoodType = 1; // 美食
        int videoTwoDimensionalSpaceType = 2;    //二次元
        int videoCarType = 3;    //汽车
        int videoGhostLivestockType = 4;    //鬼畜
        int videoNewsType = 5;    //新闻
        int videoEntertainmentType = 6;    //娱乐
        int videoMovieType = 7;    //电影
        int videoSportType = 8;    //运动
        int videoMusicType = 9;    //音乐
        int videoKnowledgeType = 10;    //知识
        int videoDanceType = 11;    //舞蹈
        int videoGameType = 12;    //游戏
        int videoTechnologyType = 13;    //科技
    }

    public interface DynamicConst {
        int NormalDynamicType = 1;
        int AutoDynamicType = 2;
    }

    public interface CommentConst {
        int CommentOnVideo = 1;
        int CommentOnComment = 2;
        int CommentOnActivity = 3;
        int CommentOnGoods = 4;
        int CommentOnDynamic = 5;
    }

    // 1点赞视频 2点赞帖子 3点赞弹幕 4点赞商品 5点赞评论
    public interface LikeConst {
        int LikeOnVideo = 1;
        int LikeOnActivity = 2;
        int LikeOnBulletScreen = 3;
        int LikeOnGoods = 4;
        int LikeOnComment = 5;
        int LikeOnDynamic = 6;
    }

    public interface CollectionConst {
        int CollectOnVideo = 1;
        int CollectOnActivity = 2;
        int CollectOnGoods = 3;
    }

    // 商品
    public interface GoodsConst {
        // 商品类型
        int GoodsFoodType = 1; // 食品类
        int GoodsClothingType = 2; // 服装类
        int GoodsElectronicType = 3; // 电子类
        int GoodsMusicalInstrumentType = 4; // 乐器类
        int GoodsSportType = 5; // 运动类
        int GoodsBookType = 6; // 书籍类
        int GoodsOtherType = 7; // 其他类

    }


    // 1待付款，2待发货，3等待确认收货, 4订单完成， 5订单取消
    public interface OrderConst {
        int OrderAnyStatus = -1;
        int OrderWaitForPayStatus = 1;
        int OrderWaitForShipStatus = 2;
        int OrderWaitForConfirmReceiptStatus = 3;
        int OrderComplete = 4;
        int OrderCancel = 5;
    }

    public interface OrderGoodsConst {
        int OrderGoodsNormalStatus = 1;
    }

    public interface LabelConst {
        int LabelUpMasterLabelType = 1;
        int LabelVideoLabelType = 2;
        int LabelSystemLabelType = 3;
        int LabelSelfDesignLabelType = 4;
    }

    public interface CommunityMemberConst {
        int CommunityMemberStatusNormal = 1;
        int CommunityMemberAdministratorAuthority = 1;
        int CommunityMemberNormalAuthority = 2;
    }

    public interface ActivityConst{
        int ActivityIsEssence = 1;
        int ActivityNotEssence = 0;
        int ActivityAnyEssence = -1;
    }

    public interface HistoryConst {
        int HistoryVideoType = 1;
        int HistoryActivityType = 2;
        int HistoryGoodsType = 3;
    }

    public interface MessageConst{
        // 来自
        String FromSystemId = "-1";

        // 1回复/点赞视频，2回复/点赞帖子，3回复/点赞商品，4回复评论，5回复动态
        int ItemTypeVideo = 1;
        int ItemTypeActivity = 2;
        int ItemTypeGoods = 3;
        int ItemTypeComment = 4;
        int ItemTypeDynamic = 5;
        // 发送者类型 1系统，2用户，3网站超级管理员
        int FromTypeSystem = 1;
        int FromTypeUser = 2;
        int FromTypeSuperAdministrator = 3;
        // 消息类型 1系统消息，2回复我的（commentId），3收到的赞（likeMapId），4用户间消息
        int MessageTypeSystem = 1;
        int MessageTypeCommentNotify = 2;
        int MessageLikeNotify = 3;
        int MessageUserCommunicate = 4;
        // isRead
        int MessageUnRead = 0;
        int MessageIsRead = 1;
    }

    public static Map<Integer, String> goodsTypeMap = new HashMap<>();

    public static Map<Integer, String> historyTypeMap = new HashMap<>();
    public static Map<Integer, String> videoTypeMap = new HashMap<>();

    static {
        Field[] fields = Constants.GoodsConst.class.getFields();
        for (Field f : fields) {
            try {
                goodsTypeMap.put(f.getInt(f.getName()), f.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        fields = Constants.HistoryConst.class.getFields();
        for (Field f : fields) {
            try {
                historyTypeMap.put(f.getInt(f.getName()), f.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        fields = Constants.VideoTypeConst.class.getFields();
        for (Field f : fields) {
            try {
                videoTypeMap.put(f.getInt(f.getName()), f.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
