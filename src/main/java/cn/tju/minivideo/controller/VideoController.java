package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.config.UploadConfig;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.handler.NonStaticResourceHttpRequestHandler;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.FileUtil;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.core.util.VideoUtils;
import cn.tju.minivideo.dto.VideoDto;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.entity.Video;
import cn.tju.minivideo.service.MediaService;
import cn.tju.minivideo.service.VideoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("video")
@AllArgsConstructor
@Slf4j
public class VideoController {
    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    @Autowired
    private VideoService videoService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private ModelMapper modelMapper;

    // video 元素支持三种视频格式： MP4, WebM, 和 Ogg:
    @GetMapping("play")
    @ApiOperation("播放视频")
    public void videoPlayerTmp(@RequestParam("name") String path, HttpServletRequest request, HttpServletResponse response) {
//        String path = request.getParameter("name");
        log.info(path);
        Path filePath = Paths.get(UploadConfig.videoPath + "/" + path);
        log.info(filePath.toString());
        if (Files.exists(filePath)) {
            String mimeType;
            try {
                mimeType = Files.probeContentType(filePath);
            } catch (IOException e) {
                throw new ControllerException(MsgEnums.INTERNAL_ERROR);
            }
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
            try {
                nonStaticResourceHttpRequestHandler.handleRequest(request, response);
            } catch (ServletException | IOException e) {
                // 经常会有 pipe broken
                log.warn(e.getMessage());
//                e.printStackTrace();
//                throw new ControllerException(MsgEnums.INTERNAL_ERROR);
            }
        }
    }

    @PostMapping("create_video")
    @ApiOperation("创建视频")
    @AuthRequired
    public Result createVideo(@RequestBody @Validated(ValidationGroups.Insert.class) VideoDto videoDto) {
        Video video = modelMapper.map(videoDto, Video.class);
        User user = JwtInterceptor.getUser();
        video.setUserId(user.getUserId());
        if (!mediaService.isExistByMediaUrlAndTrueFile(video.getAvatar()) || !mediaService.isExistByMediaUrlAndTrueFile(video.getVideoFile())) {
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        video.setFileSize(FileUtil.getFileSizeByUrl(video.getVideoFile()));
        video.setVideoSize(VideoUtils.getVideoDuration(video.getVideoFile()));
        videoService.insertSelective(video);
        return Results.OkWithData(video.getVideoId());
    }

}
