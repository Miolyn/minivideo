package cn.tju.minivideo.core.constants;

public class AlgorithmConst {
    public static String BaseUrl = "http://175.27.191.103:8000/";
    public static String PicBaseUrl = "http://175.27.191.103:9011/";
//    public static String RecommendUrl = BaseUrl + "recommend/%d/%d";

    public interface Recommend{
        String RecommendVideoUrl = BaseUrl + "recommend/%d/%d";
        String RecommendGoodsUrl = BaseUrl + "good_recommend/%d/%d";
        Integer typeUpdateVideo = 1;
        Integer typeUpdateHistory = 2;
        Integer Any = 0;
    }

    public interface ContentCheck{
        String ContentCheckUrl = BaseUrl + "check_content";
        String NewText = "new_text";
        String Result = "result";
    }

    public interface PictureCheck{
        String PictureCheckUrl = PicBaseUrl + "check";
        String Res = "res";
    }

//    public static Integer typeUpdateVideo = 1;
//    public static Integer typeUpdateHistory = 2;
//    public static Integer Any = 0;


}
