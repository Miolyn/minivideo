package cn.tju.minivideo.dto;

import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoGoodsRecommendDto {
    @NotNull(message = "视频推荐商品的id不能为空", groups = {ValidationGroups.IdForm.class})
    private Integer videoGoodsRecommendId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 视频id
     */
    @NotNull(message = "视频id不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private Integer videoId;

    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private Integer goodsId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
