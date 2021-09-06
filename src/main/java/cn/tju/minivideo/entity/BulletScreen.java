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
public class BulletScreen implements Serializable {
    /**
     * 弹幕id
     */
    private Integer bulletScreenId;

    /**
     * 弹幕内容
     */
    private String content;

    /**
     * 发布人id
     */
    private String userId;

    /**
     * 发弹幕的video_id
     */
    private Integer videoId;

    /**
     * 在视频中发弹幕的时间
     */
    private Integer videoTime;

    /**
     * 弹幕颜色
     */
    private String color;

    /**
     * 弹幕大小
     */
    private Integer size;

    /**
     * 弹幕位置
     */
    private Integer position;

    /**
     * 点赞数量
     */
    private Integer likeNum;

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