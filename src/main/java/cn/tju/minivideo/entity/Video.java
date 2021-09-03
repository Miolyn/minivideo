package cn.tju.minivideo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Video implements Serializable {
    private Integer videoId;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频
     */
    private String avatar;

    /**
     * 存储视频的路径
     */
    private String videoFile;

    /**
     * 发布人的id
     */
    private String userId;

    /**
     * 视频类型 1美食类......
     */
    private Integer videoType;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 视频文件大小 单位MB
     */
    private Integer fileSize;

    /**
     * 视频长度 单位秒
     */
    private Integer videoSize;

    /**
     * 播放数量
     */
    private Integer playNum;

    /**
     * 点赞数量
     */
    private Integer likeNum;

    /**
     * 收藏数
     */
    private Integer collectNum;

    /**
     * 评论数量
     */
    private Integer commentNum;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}