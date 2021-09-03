package cn.tju.minivideo.dto;

import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDto {

    /**
     * 对象id
     */
    @NotNull(message = "收藏对象id不能为空", groups = ValidationGroups.Insert.class)
    private Integer itemId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 收藏的对象， 1视频 2帖子
     */
    @NotNull(message = "收藏对象类型不能为空", groups = ValidationGroups.Insert.class)
    @Range(min=1, max=2, message = "1收藏视频，2收藏帖子",groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private Integer itemType;
}
