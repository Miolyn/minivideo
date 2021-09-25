package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.config.UploadConfig;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.FileUtil;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.entity.Media;
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("upload")
@Slf4j
public class UploadController {

    @Autowired
    private MediaService mediaService;

    @PostMapping("img_upload")
    @AuthRequired
    @Transactional
    public Result imgUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new ControllerException(MsgEnums.UPLOAD_FORMAT_ERROR);
        }
        // 原始文件名称
        String fileName = file.getOriginalFilename();
        // 解析到文件后缀，判断是否合法
        int index = fileName.lastIndexOf(".");
        String suffix;
        if (index == -1 || (suffix = fileName.substring(index + 1)).isEmpty()) {
            throw new ControllerException(MsgEnums.UPLOAD_FORMAT_ERROR);
        }
        if (!UploadConfig.imgAllowSuffix.contains(suffix.toLowerCase())) {
            throw new ControllerException(MsgEnums.UPLOAD_FORMAT_ERROR);
        }
        // 准备存放路径
        String newFileName = FileUtil.uploadGetNewName(fileName);
        String newPath = FileUtil.uploadGetPath(UploadConfig.imgPath);
        File folder = FileUtil.uploadMkdirs(newPath);
        File newFile = new File(folder, newFileName);
        try {
            file.transferTo(newFile.getAbsoluteFile());
        } catch (IOException e) {
            log.warn(e.getMessage());
            throw new ControllerException(MsgEnums.UPLOAD_SAVE_FILE_ERROR);
        }
        String absPath = newFile.getAbsolutePath();
        String relativePath = absPath.substring(absPath.lastIndexOf(UploadConfig.imgUrlBase));
        User user = JwtInterceptor.getUser();
        try {
            mediaService.insertSelective(new Media(relativePath, Constants.UploadConst.UploadImgType, user.getUserId()));
        } catch (Exception e) {
            log.warn(e.getMessage());
            newFile.delete();
            throw new ControllerException(MsgEnums.INTERNAL_ERROR);
        }
        return Results.OkWithData(relativePath);
    }

    // TODO: 后期如果可以的话可以换成 分块上传
    @PostMapping("video_upload")
    @AuthRequired
    @Transactional
    public Result videoUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new ControllerException(MsgEnums.UPLOAD_FORMAT_ERROR);
        }
        // 原始文件名称
        String fileName = file.getOriginalFilename();
        // 解析到文件后缀，判断是否合法
        int index = fileName.lastIndexOf(".");
        String suffix;
        if (index == -1 || (suffix = fileName.substring(index + 1)).isEmpty()) {
            throw new ControllerException(MsgEnums.UPLOAD_FORMAT_ERROR);
        }
        if (!UploadConfig.videoAllowSuffix.contains(suffix.toLowerCase())) {
            throw new ControllerException(MsgEnums.UPLOAD_FORMAT_ERROR);
        }

        // 准备存放路径
        String newFileName = FileUtil.uploadGetNewName(fileName);
        String newPath = FileUtil.uploadGetPath(UploadConfig.videoPath);
        File folder = FileUtil.uploadMkdirs(newPath);
        File newFile = new File(folder, newFileName);
        try {
            file.transferTo(newFile.getAbsoluteFile());
        } catch (IOException e) {
            log.warn(e.getMessage());
            throw new ControllerException(MsgEnums.UPLOAD_SAVE_FILE_ERROR);
        }
        String absPath = newFile.getAbsolutePath();
        String relativePath = absPath.substring(absPath.lastIndexOf(UploadConfig.videoUrlBase));
        User user = JwtInterceptor.getUser();
        try {
            mediaService.insertSelective(new Media(relativePath, Constants.UploadConst.UploadVideoType, user.getUserId()));
        } catch (Exception e) {
            log.warn(e.getMessage());
            newFile.delete();
            throw new ControllerException(MsgEnums.INTERNAL_ERROR);
        }
        return Results.OkWithData(relativePath);
    }

    @PostMapping("imgs_upload")
    @AuthRequired
    @Transactional
    public Result imgsUpload(@RequestParam(value = "file") MultipartFile[] files) {
        List<String> data = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
                throw new ControllerException(MsgEnums.UPLOAD_FORMAT_ERROR);
            }
            // 原始文件名称
            String fileName = file.getOriginalFilename();
            // 解析到文件后缀，判断是否合法
            int index = fileName.lastIndexOf(".");
            String suffix;
            if (index == -1 || (suffix = fileName.substring(index + 1)).isEmpty()) {
                throw new ControllerException(MsgEnums.UPLOAD_FORMAT_ERROR);
            }
            if (!UploadConfig.imgAllowSuffix.contains(suffix.toLowerCase())) {
                throw new ControllerException(MsgEnums.UPLOAD_FORMAT_ERROR);
            }
            // 准备存放路径
            String newFileName = FileUtil.uploadGetNewName(fileName);
            String newPath = FileUtil.uploadGetPath(UploadConfig.imgPath);
            File folder = FileUtil.uploadMkdirs(newPath);
            File newFile = new File(folder, newFileName);
            try {
                file.transferTo(newFile.getAbsoluteFile());
            } catch (IOException e) {
                log.warn(e.getMessage());
                throw new ControllerException(MsgEnums.UPLOAD_SAVE_FILE_ERROR);
            }
            String absPath = newFile.getAbsolutePath();
            String relativePath = absPath.substring(absPath.lastIndexOf(UploadConfig.imgUrlBase));
            User user = JwtInterceptor.getUser();
            try {
                mediaService.insertSelective(new Media(relativePath, Constants.UploadConst.UploadImgType, user.getUserId()));
            } catch (Exception e) {
                log.warn(e.getMessage());
                newFile.delete();
                throw new ControllerException(MsgEnums.INTERNAL_ERROR);
            }
            data.add(relativePath);
        }

        return Results.OkWithData(data);
    }
}
