package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.config.UploadConfig;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.constants.ProjectConstant;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.handler.NonStaticResourceHttpRequestHandler;
import cn.tju.minivideo.core.interceptor.JwtInterceptor;
import cn.tju.minivideo.core.util.*;
import cn.tju.minivideo.dto.*;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import cn.tju.minivideo.entity.*;
import cn.tju.minivideo.service.*;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

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
    private DynamicService dynamicService;

    @Autowired
    private BulletScreenService bulletScreenService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private VideoGoodsRecommendService videoGoodsRecommendService;

    @Autowired
    private GoodsService goodsService;

    // video 元素支持三种视频格式： MP4, WebM, 和 Ogg:
    // TODO 增加视频播放量
    @GetMapping("play_tmp")
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

    @GetMapping("play")
    @ApiOperation("播放指定videoId的视频")
    public void videoPlayer(@RequestParam("videoId") Integer videoId, HttpServletRequest request, HttpServletResponse response) {
        Video video = videoService.selectByPrimaryKey(videoId);
        if (video == null) {
            throw new ControllerException(MsgEnums.VIDEO_NOT_FOUND);
        }
        String path = FileUtil.getUploadFilePath(video.getVideoFile());
        Path filePath = Paths.get(path);
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
            videoService.addVideoPlayNumByVideoId(videoId);
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

    @PostMapping("add_play_num")
    @ApiOperation("增加1视频播放量")
    public Result addVideoPlayNum(@RequestBody @Validated(ValidationGroups.IdForm.class) VideoDto videoDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
        videoService.addVideoPlayNumByVideoId(videoDto.getVideoId());
        return Results.Ok();
    }

    @PostMapping("create_video")
    @ApiOperation("创建视频")
    @AuthRequired
    @Transactional
    public Result createVideo(@RequestBody @Validated(ValidationGroups.Insert.class) VideoDto videoDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
        Video video = modelMapper.map(videoDto, Video.class);
        User user = JwtInterceptor.getUser();
        video.setUserId(user.getUserId());
        if (!mediaService.isExistByMediaUrlAndMediaTypeAndTrueFile(video.getAvatar(), Constants.UploadConst.UploadImgType)
                || !mediaService.isExistByMediaUrlAndMediaTypeAndTrueFile(video.getVideoFile(), Constants.UploadConst.UploadVideoType)
                || !ConstUtil.isVideoTypeValid(videoDto.getVideoType())) {
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        video.setFileSize(FileUtil.getFileSizeByUrl(video.getVideoFile()));
        video.setVideoSize(VideoUtils.getVideoDuration(video.getVideoFile()));
        videoService.insertSelective(video);
        dynamicService.createVideoAutoDynamic(user.getUserId(), video.getVideoId());
        return Results.OkWithData(video.getVideoId());
    }

    @PostMapping("update_video")
    @ApiOperation("更新视频信息")
    @AuthRequired
    @Transactional
    public Result updateVideoProfile(@RequestBody @Validated(ValidationGroups.Update.class) VideoDto videoDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
        User user = JwtInterceptor.getUser();
        Video video = modelMapper.map(videoDto, Video.class);
        if (!videoService.checkPermissionToUpdateVideoProfile(video.getVideoId(), user.getUserId())) {
            throw new ControllerException(MsgEnums.PERMISSION_ERROR);
        }
        videoService.updateByPrimaryKeySelective(video);
        return Results.Ok();
    }

    @PostMapping("delete_video")
    @ApiOperation("视频下架")
    @Transactional
    public Result deleteVideo(@RequestBody @Validated(ValidationGroups.IdForm.class) VideoDto videoDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
        User user = JwtInterceptor.getUser();
        if (!videoService.checkPermissionToUpdateVideoProfile(videoDto.getVideoId(), user.getUserId())) {
            throw new ControllerException(MsgEnums.PERMISSION_ERROR);
        }
        videoService.deleteByPrimaryKey(videoDto.getVideoId());
        return Results.Ok();
    }

    // TODO: 增加播放历史和增加播放次数
    @GetMapping("video_detail")
    @AuthRequired(required = false)
    @ApiOperation("获取单个视频详情")
    public Result getVideoDetailInfo(@RequestParam(value = "videoId") Integer videoId) {
        Video video = videoService.selectByPrimaryKey(videoId);
        if (video == null) {
            throw new ControllerException(MsgEnums.VIDEO_NOT_FOUND);
        }
        VideoDto videoDto = modelMapper.map(video, VideoDto.class);
        if (JwtInterceptor.getUser() != null){
            historyService.addHistory(JwtInterceptor.getUser().getUserId(), videoId, Constants.HistoryConst.HistoryVideoType);
        }
        return Results.OkWithData(videoDto);
    }

    @GetMapping("video_simple")
    @ApiOperation("获取单个视频详情")
    public Result getVideoSimpleInfo(@RequestParam(value = "videoId") Integer videoId) {
        Video video = videoService.selectByPrimaryKey(videoId);
        if (video == null) {
            throw new ControllerException(MsgEnums.VIDEO_NOT_FOUND);
        }
        SimpleVideoDto simpleVideoDto = modelMapper.map(video, SimpleVideoDto.class);
        return Results.OkWithData(simpleVideoDto);
    }

    @GetMapping("user_videos")
    @AuthRequired(required = false)
    @ApiOperation("获取用户发布的视频列表，按顺序排序")
    public Result getUserVideosOrderByMethod(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
                                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                @RequestParam(value = "sort", defaultValue = "1") Integer sortMethod) {
        if (userId.equals("")) {
            User user = JwtInterceptor.getUser();
            if (user == null) {
                throw new ControllerException(MsgEnums.VALIDATION_ERROR);
            }
            userId = user.getUserId();
        }
        PageInfo<Video> pageInfo = videoService.getVideosByUserIdWithPaginatorSortByMethod(userId, page, ProjectConstant.PageSize, sortMethod);
//        List<Integer> videoIds = new ArrayList<>();
        List<SimpleVideoDto> simpleVideoDtos = new ArrayList<>();
        for (Video video : pageInfo.getList()) {
            SimpleVideoDto simpleVideoDto = modelMapper.map(video, SimpleVideoDto.class);
            simpleVideoDtos.add(simpleVideoDto);
        }
//        pageInfo.getList().forEach(video -> videoIds.add(video.getVideoId()));
        return Results.OkWithData(Paginators.paginator(pageInfo, simpleVideoDtos));
    }

    @GetMapping("videos")
    @ApiOperation("获取所有视频的列表，按顺序排序，分页")
    public Result getVideoOrderBySortMethod(@RequestParam(value = "videoType", defaultValue = "-1") Integer videoType,
                                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "sort", defaultValue = "1") Integer sortMethod){
        if(!videoType.equals(-1) && !ConstUtil.isVideoTypeValid(videoType)){
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        PageInfo<Video> pageInfo = videoService.getVideosByVideoTypeWithPaginatorSortByMethod(videoType, page, ProjectConstant.PageSize, sortMethod);
        List<SimpleVideoDto> data = new ArrayList<>();
        pageInfo.getList().forEach(video -> data.add(modelMapper.map(video, SimpleVideoDto.class)));
        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }

    // TODO: 根据视频id获取推荐商品，从用户上传商品中随机推荐
    @GetMapping("video_goods_recommends")
    @ApiOperation("获取视频对应的推荐商品")
    public Result getVideoGoodsRecommends(@RequestParam(value = "videoId", defaultValue = "-1") Integer videoId){
        if(videoId.equals(-1) || !videoService.isVideoExistByVideoId(videoId)){
            throw new ControllerException(MsgEnums.VALIDATION_ERROR);
        }
        Random random = new Random();
        String userId = videoService.getUserIdOfrVideoByVideoId(videoId);
        List<Goods> goods = goodsService.getGoodsByUserId(userId);
        Set<Goods> total = new HashSet<>();
        while (total.size() < 3){
            total.add(goods.get(random.nextInt(goods.size())));
        }
        List<SimpleGoodsDto> data = new ArrayList<>();
        for (Goods goods1 : total) {
            data.add(modelMapper.map(goods1, SimpleGoodsDto.class));
        }
        return Results.OkWithData(data);
//        List<VideoGoodsRecommend> videoGoodsRecommends = videoGoodsRecommendService.getVideoGoodsRecommendsByVideoId(videoId);
//        List<SimpleGoodsDto> data = new ArrayList<>();
//        for (VideoGoodsRecommend videoGoodsRecommend : videoGoodsRecommends) {
//            Goods goods = goodsService.getGoodsByGoodsId(videoGoodsRecommend.getGoodsId());
//            data.add(modelMapper.map(goods, SimpleGoodsDto.class));
//        }
//        return Results.OkWithData(data);
    }

    // TODO: 添加
    @PostMapping("add_video_goods_recommends")
    @ApiOperation("添加视频商品推荐商品")
    @AuthRequired
    public Result addVideoGoodsRecommends(@RequestBody @Validated(ValidationGroups.Insert.class) VideoGoodsRecommendDto videoGoodsRecommendDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
        String userId = JwtInterceptor.getUser().getUserId();
        VideoGoodsRecommend videoGoodsRecommend = modelMapper.map(videoGoodsRecommendDto, VideoGoodsRecommend.class);
        videoGoodsRecommend.setUserId(userId);
        videoGoodsRecommendService.insertSelective(videoGoodsRecommend);
        return Results.OkWithData(videoGoodsRecommend.getVideoGoodsRecommendId());
    }

    // TODO: 根据推荐id修改
    @PostMapping("update_video_goods_recommends")
    @ApiOperation("修改视频商品推荐")
    @AuthRequired
    public Result modifyVideoGoodsRecommend(@RequestBody @Validated(ValidationGroups.Update.class) VideoGoodsRecommendDto videoGoodsRecommendDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);

        return Results.Ok();
    }


    // TODO: 根据id软删除
    @PostMapping("delete_video_goods_recommends")
    @ApiOperation("删除视频商品推荐")
    @AuthRequired
    public Result deleteVideoGoodsRecommend(@RequestBody @Validated(ValidationGroups.IdForm.class) VideoGoodsRecommendDto videoGoodsRecommendDto, BindingResult bindingResult){
        BindUtil.checkBindValid(bindingResult);
        videoGoodsRecommendService.deleteByPrimaryKeyLogical(videoGoodsRecommendDto.getVideoGoodsRecommendId());
        return Results.Ok();
    }


    @PostMapping("bullet_screen")
    @ApiOperation("创建弹幕")
    @AuthRequired
    public Result sendBulletScreen(@RequestBody @Validated(ValidationGroups.Insert.class) BulletScreenDto bulletScreenDto, BindingResult bindingResult) {
        BindUtil.checkBindValid(bindingResult);
        String userId = JwtInterceptor.getUser().getUserId();
        BulletScreen bulletScreen = modelMapper.map(bulletScreenDto, BulletScreen.class);
        bulletScreen.setUserId(userId);
        bulletScreenService.insertSelective(bulletScreen);
        return Results.Ok();
    }

    @GetMapping("bullet_screen")
    @ApiOperation("获取弹幕")
    public Result getBulletScreens(@RequestParam(value = "videoId") Integer videoId) {
        if (!videoService.isVideoExistByVideoId(videoId)) {
            throw new ControllerException(MsgEnums.ITEM_NOT_EXIST);
        }
        List<BulletScreen> list = bulletScreenService.findAllByVideoId(videoId);
        List<BulletScreenDto> data = new ArrayList<>();
        list.forEach(bulletScreen -> data.add(modelMapper.map(bulletScreen, BulletScreenDto.class)));
        return Results.OkWithData(data);
    }

    @GetMapping("bullet_screen_page")
    @ApiOperation("按分页获取弹幕信息")
    public Result getBulletScreensByWithPaginator(@RequestParam(value = "videoId") Integer videoId,
                                                  @RequestParam(value = "page") Integer page) {
        if (!videoService.isVideoExistByVideoId(videoId)) {
            throw new ControllerException(MsgEnums.ITEM_NOT_EXIST);
        }
        PageInfo<BulletScreen> pageInfo = bulletScreenService.findByVideoIdWithPaginator(videoId, page, ProjectConstant.PageSize);
        List<BulletScreenDto> data = new ArrayList<>();
        pageInfo.getList().forEach(bulletScreen -> data.add(modelMapper.map(bulletScreen, BulletScreenDto.class)));
        return Results.OkWithData(Paginators.paginator(pageInfo, data));
    }
}
