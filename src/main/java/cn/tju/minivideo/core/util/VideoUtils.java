package cn.tju.minivideo.core.util;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class VideoUtils {
    private static ImmutableList<String> videoSuffixList = ImmutableList.of("mp4", "mov", "avi", "mkv", "m4v", "wmv",
            "asf", "asx", "rm", "rmvb", "3gp", "dat", "flv", "vob");
    /**
     * 获取视频时长 * @param file 视频文件
     * @return 时长（秒）
     */ public static int getVideoDuration(String url){
         File file = new File(FileUtil.getUploadFilePath(url));
        int duration = 0;
        try{
            MultimediaObject multimediaObject = new MultimediaObject(file);
            MultimediaInfo info = multimediaObject.getInfo();
            duration = (int) Math.ceil((double) info.getDuration() / 1000);
        } catch (Exception e){
            log.error("获取视频时长失败", e);
        }
        return duration;
    }
    /**
     * 判断文件是否是视频 * @param file 文件
     * @return 是否是啊视频
     */ public static boolean isVideo(File file){
        return videoSuffixList.contains(FilenameUtils.getExtension(file.getName()));
    }

    // TODO: 未完成
    public int transcode(String filePath, String outputPath){
        //3、视频转码
        List<String> ffmpegCmds = new LinkedList<>();
        ffmpegCmds.add("ffmpeg");
        ffmpegCmds.add("-i");
//        ffmpegCmds.add(bootstrapConfig.getFileRoot() + filePath);
        ffmpegCmds.add(filePath);
        ffmpegCmds.add("-c:v");
        ffmpegCmds.add("libx264");
        ffmpegCmds.add("-strict");
        ffmpegCmds.add("-2");
        ffmpegCmds.add(outputPath);
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(ffmpegCmds);
        return 1;
    }


}
