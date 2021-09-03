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
public class VideoDto {

    @NotNull(message = "视频Id不能为空", groups = {ValidationGroups.IdForm.class, ValidationGroups.Update.class})
    private Integer videoId;


    /**
     * 视频标题
     */
    @NotBlank(message = "视频标题不能为空", groups = ValidationGroups.Insert.class)
    @Length(min = 1, max = 100, message = "视频标题长度范围0-100", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String title;
    /**
     * 视频
     */
    @NotBlank(message = "视频封面不能为空", groups = ValidationGroups.Insert.class)
    @Length(min = 1, max = 255, message = "视频封面url长度范围0-255", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String avatar;

    /**
     * 存储视频的路径
     */
    @Length(min = 1, max = 255, message = "视频文件长度范围0-255", groups = {ValidationGroups.Insert.class})
    @Null(message = "不能修改视频文件", groups = {ValidationGroups.Update.class})
    private String videoFile;

    /**
     * 发布人的id
     */
    private String userId;

    /**
     * 简介
     */
    @Length(min = 1, max = 1000, message = "视频简介长度范围0-1000", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String introduction;

    /**
     * 视频类型 1美食类......
     */
    @Range(min=1, max=20, message = "视频类型范围在1-20，1:美食",groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    @Null(message = "不能修改视频类型", groups = ValidationGroups.Update.class)
    private Integer videoType;

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
}
