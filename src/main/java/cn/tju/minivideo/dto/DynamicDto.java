package cn.tju.minivideo.dto;

import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DynamicDto {
    @Null(message = "没有权限修改", groups = {ValidationGroups.Insert.class})
    private Integer dynamicId;

    /**
     * 用户id
     */
    @Null(message = "没有权限修改", groups = {ValidationGroups.Insert.class})
    private String userId;

    /**
     * 内容 若为自动动态  则内容则填写视频id
     */
    @NotBlank(message = "动态内容不能为空", groups = {ValidationGroups.Insert.class})
    @Length(min = 1, max = 1000, message = "动态内容长度范围1-1000", groups = {ValidationGroups.Insert.class})
    private String content;

    /**
     * 动态类型 1普通动态 2自动动态（up主发了视频）
     */
    @Null(message = "没有权限修改", groups = {ValidationGroups.Insert.class})
    private Integer dynamicType;

    @Null(message = "没有权限修改", groups = {ValidationGroups.Insert.class})
    private Integer likeNum;

    @Null(message = "没有权限修改", groups = {ValidationGroups.Insert.class})
    private Integer commentNum;

    @Null(message = "没有权限修改", groups = {ValidationGroups.Insert.class})
    private Integer collectNum;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
