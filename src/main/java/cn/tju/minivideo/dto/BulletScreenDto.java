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
//    @JsonProperty(value = "text")
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
     * 弹幕的配置属性
     */
    @NotBlank(message = "视频配置属性不能为空", groups = ValidationGroups.Insert.class)
    @Length(min = 1, max = 1000, message = "配置属性长度范围1-1000", groups = {ValidationGroups.Insert.class})
    private String options;
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
