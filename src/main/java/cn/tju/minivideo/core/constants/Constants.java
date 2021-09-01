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

}
