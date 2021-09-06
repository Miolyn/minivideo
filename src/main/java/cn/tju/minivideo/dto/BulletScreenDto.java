package cn.tju.minivideo.dto;

import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BulletScreenDto {
    /**
     * 弹幕id
     */
    private Integer bulletScreenId;

    /**
     * 弹幕内容
     */
    @NotBlank(message = "弹幕内容不能为空", groups = ValidationGroups.Insert.class)
    @Length(min = 1, max = 255, message = "弹幕内容长度为1-255",groups = ValidationGroups.Insert.class)
    @JsonProperty(value = "text")
    private String content;

    /**
     * 发布人id
     */
    private String userId;

    /**
     * 发弹幕的video_id
     */
    @NotNull(message = "视频id不能为空", groups = ValidationGroups.Insert.class)
    private Integer videoId;

    /**
     * 在视频中发弹幕的时间
     */
    @NotNull(message = "弹幕在视频中的时间不能为空", groups = ValidationGroups.Insert.class)
    @JsonProperty(value = "time")
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
    /**
     * 点赞数量
     */
    @Null(message = "不能更新点赞数量", groups = ValidationGroups.Insert.class)
    private Integer likeNum;


    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
