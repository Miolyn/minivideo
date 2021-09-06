package cn.tju.minivideo.core.constants;

import com.google.common.base.Charsets;

import java.nio.charset.Charset;

public class Constants {
    public static final String APP_NAME = "miniVideo";

    public static final Charset CHARSET = Charsets.UTF_8;


    public interface Flag {
        Integer YES = 1;
        Integer NO = 2;
    }

    // user
    public interface UserConst {
        // status 1正常 2封号
        Integer StatusNormal = 1;
        Integer StatusBanned = 2;
        Integer SexMale = 1;
        Integer SexFemale = 2;

    }

    public interface RelationConst {
        Integer FollowRelation = 1;

    }


    public interface UploadConst {
        Integer UploadImgType = 1;
        Integer UploadVideoType = 2;
    }

    public interface VideoConst {
        Integer VideoFoodType = 1;
    }

    public interface DynamicConst {
        Integer NormalDynamicType = 1;
        Integer AutoDynamicType = 2;
    }

    public interface CommentConst {
        Integer CommentOnVideo = 1;
        Integer CommentOnComment = 2;
        Integer CommentOnActivity = 3;
    }

    // 1点赞视频 2点赞帖子 3点赞弹幕 4点赞商品 5点赞评论
    public interface LikeConst{
        Integer LikeOnVideo = 1;
        Integer LikeOnActivity = 2;
        Integer LikeOnBulletScreen = 3;
        Integer LikeOnGoods = 4;
        Integer LikeOnComment = 5;
    }

    public interface CollectionConst{
        Integer CollectOnVideo = 1;
        Integer CollectOnActivity = 2;
    }
}
