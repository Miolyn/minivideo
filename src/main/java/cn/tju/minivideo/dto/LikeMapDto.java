package cn.tju.minivideo.dto;

import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeMapDto {
    /**
     * 点赞人id
     */
    private String fromId;

    /**
     * 点赞对象id
     */
    @NotBlank(message = "点赞对象id不能为空", groups = ValidationGroups.Insert.class)
    private Integer toId;

    /**
     * 点赞对象类型 1点赞视频 2点赞帖子 3点赞弹幕 4点赞商品
     */
    @NotBlank(message = "点赞对象类型不能为空", groups = ValidationGroups.Insert.class)
    @Range(min=1, max=4, message = "1点赞视频，2点赞帖子，3点赞弹幕，4点赞商品",groups = {ValidationGroups.Insert.class})
    private Integer likeType;
}
