package cn.tju.minivideo.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
//@ConfigurationProperties(prefix = "upload")
public class UploadConfig {

    public static String basePath;

    public static String imgPath;

    public static String imgUrlBase;


    public static String videoPath;

    public static String videoUrlBase;


    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    public static Set<String> imgAllowSuffix = new HashSet<>(Arrays.asList("jpg", "jpeg", "png", "gif"));
    public static Set<String> videoAllowSuffix = new HashSet<>(Arrays.asList("mp4"));

    public static String getBasePath() {
        return basePath;
    }
    @Value("${upload.base-path}")
    public void setBasePath(String basePath) {
        UploadConfig.basePath = basePath;
    }

    public static String getImgPath() {
        return imgPath;
    }
    @Value("${upload.img-path}")
    public void setImgPath(String imgPath) {
        UploadConfig.imgPath = imgPath;
    }

    public static String getVideoPath() {
        return videoPath;
    }

    @Value("${upload.video-path}")
    public void setVideoPath(String videoPath) {
        UploadConfig.videoPath = videoPath;
    }

    public static String getImgUrlBase() {
        return imgUrlBase;
    }

    @Value("${upload.img-url-base}")
    public void setImgBasePath(String imgBasePath) {
        UploadConfig.imgUrlBase = imgBasePath;
    }

    public static String getVideoUrlBase() {
        return videoUrlBase;
    }

    @Value("${upload.video-url-base}")
    public void setVideoBasePath(String videoBasePath) {
        UploadConfig.videoUrlBase = videoBasePath;
    }
}
