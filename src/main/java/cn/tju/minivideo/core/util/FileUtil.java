package cn.tju.minivideo.core.util;

import cn.tju.minivideo.core.config.UploadConfig;
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
}
