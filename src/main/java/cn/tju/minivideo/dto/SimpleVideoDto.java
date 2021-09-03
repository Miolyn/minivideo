package cn.tju.minivideo.dto;

import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleVideoDto {

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
     * 发布人的id
     */
    private String userId;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 视频类型 1美食类......
     */
    private Integer videoType;

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
     * 创建时间
     */
    private LocalDateTime createdAt;
}
