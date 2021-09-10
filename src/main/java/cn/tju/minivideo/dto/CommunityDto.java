package cn.tju.minivideo.dto;

import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityDto {
    @NotNull(message = "社区id不能为空", groups = {ValidationGroups.Update.class, ValidationGroups.IdForm.class})
    private Integer communityId;

    /**
     * 社区名称
     */
    // 若为up主类型社区不能改名
    @NotEmpty(message = "社区名称不能为空", groups = {ValidationGroups.Insert.class})
    @Length(min = 1, max = 255, message = "社区名称长度范围1-255", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String communityName;

    /**
     * 社区简介
     */
    @NotEmpty(message = "社区简介不能为空", groups = {ValidationGroups.Insert.class})
    @Length(min = 1, max = 255, message = "社区简介长度范围1-255", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String introduction;

    /**
     * 社区封面
     */
    @NotEmpty(message = "社区封面不能为空", groups = {ValidationGroups.Insert.class})
    @Length(min = 1,max = 255, message = "社区封面长度范围1-255", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String avatar;

    /**
     * 创建人id
     */
    @Null(message = "没有权限修改", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String userId;

    /**
     * 成员数量
     */
    @Null(message = "没有权限修改", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private Integer memberNum;

    /**
     * 帖子数量
     */
    @Null(message = "没有权限修改", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private Integer activityNum;

    /**
     * 主标签
     */
    @Null(message = "没有权限修改", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private Integer mainLabelId;

    /**
     * 自定义标签
     */
    @Null(message = "没有权限修改", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String selfLabelIds;

    /**
     * 自定义标签列表
     */
    // 若是up主标签则只需要有labelType即可，其余的则需要labelId
    @NotNull(message = "主标签不能为空", groups = {ValidationGroups.Insert.class})
    @Null(message = "没有权限修改", groups = {ValidationGroups.Update.class})
    LabelDto mainLabel;

    /**
     * 自定义标签列表
     */
    @NotNull(message = "副标签不能为null, 需要空数组[]", groups = {ValidationGroups.Insert.class})
    @Null(message = "没有权限修改", groups = {ValidationGroups.Update.class})
    List<String> selfLabels;

    @Null(message = "没有权限修改", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    List<LabelDto> selfLabelDtos;

    /**
     * 创建时间
     */
    @Null(message = "没有权限修改", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private LocalDateTime createdAt;
}
