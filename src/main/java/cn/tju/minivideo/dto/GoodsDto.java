package cn.tju.minivideo.dto;

import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDto {
    private Integer goodsId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 商品标题
     */
    @NotBlank(message = "商品标题不能为空", groups = {ValidationGroups.Insert.class})
    private String title;

    /**
     * 商品图标
     */
    @NotBlank(message = "商品封面不能为空", groups = {ValidationGroups.Insert.class})
    private String avatar;

    /**
     * 商品类别
     */
    @NotNull(message = "商品类型不能为空", groups = {ValidationGroups.Insert.class})
    private Integer goodsType;

    /**
     * 简介
     */
    @NotBlank(message = "商品简介不能为空", groups = {ValidationGroups.Insert.class})
    private String introduction;

    /**
     * 多张图片的url
     */
    @NotBlank(message = "商品图片不能为空", groups = {ValidationGroups.Insert.class})
    private List<String> imgs;

    /**
     * 内容，可以用markdown表示
     */
    private String content;

    /**
     * 点赞数量
     */
    private Integer likeNum;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
