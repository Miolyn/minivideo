package cn.tju.minivideo.core.util;

import cn.tju.minivideo.core.config.UploadConfig;
import cn.tju.minivideo.core.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;
import java.util.UUID;

public class FileUtil {


    public static String uploadGetPath(String basePath){
        String dateFormat = UploadConfig.dateFormat.format(new Date());
        return basePath + dateFormat;
    }
    public static File uploadMkdirs(String path){
        File folder = new File(path);
        if (!folder.isDirectory()){
            folder.mkdirs();
        }
        return folder;
    }


    public static String uploadGetNewName(String oldName){
        return UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."));
    }


    public static String getUploadFilePath(String url){
        // src/main/resources/static/ + url
        return UploadConfig.basePath + url;
    }

    // 获取文件大小 返回MB
    public static Integer getFileSizeByUrl(String url){
        String path = getUploadFilePath(url);
        File file = new File(path);
        return Math.toIntExact(file.length() / 1024 / 1024);
    }

    public static boolean isFileExist(String fileName){
        return new File(fileName).isFile();
    }

    public String getExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


    public String getFileDir(String filePath) {
        return filePath.substring(0, filePath.lastIndexOf("."));
    }


    public String getFileName(String filePath) {
        return filePath.substring(filePath.lastIndexOf(".") + 1, filePath.lastIndexOf("."));
    }
}
