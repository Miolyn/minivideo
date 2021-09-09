package cn.tju.minivideo.dto;

import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDto {

    @NotNull(message = "商品id不能为空", groups = {ValidationGroups.Update.class})
    private Integer goodsId;

    /**
     * 用户id
     */
    @Null(message = "没有权限修改用户id", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String userId;

    /**
     * 商品标题
     */
    @NotBlank(message = "商品标题不能为空", groups = {ValidationGroups.Insert.class})
    @Length(min = 1, max = 255, message = "商品标题长度范围1-255", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String title;

    /**
     * 商品价格
     */
    @NotNull(message = "商品价格不能为空", groups = {ValidationGroups.Insert.class})
    private BigDecimal price;

    /**
     * 商品图标
     */
    @NotBlank(message = "商品封面不能为空", groups = {ValidationGroups.Insert.class})
    @Length(min = 1, max = 255, message = "商品封面url长度范围1-255", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String avatar;

    /**
     * 商品类别
     */
    @NotNull(message = "商品类型不能为空", groups = {ValidationGroups.Insert.class})
    @Null(message = "不能修改商品类型", groups = {ValidationGroups.Update.class})
    private Integer goodsType;

    /**
     * 简介
     */
    @NotBlank(message = "商品简介不能为空", groups = {ValidationGroups.Insert.class})
    @Length(min = 1, max = 255, message = "商品简介长度范围1-255", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String introduction;

    /**
     * 多张图片的url
     */
    @NotNull(message = "商品图片不能为空", groups = {ValidationGroups.Insert.class})
    private List<String> imgs;

    /**
     * 内容，可以用markdown表示
     */
    @NotBlank(message = "商品内容不能为空", groups = {ValidationGroups.Insert.class})
    private String content;

    /**
     * 点赞数量
     */
    @Null(message = "没有权限修改点赞数量", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private Integer likeNum;

    /**
     * 收藏数量
     */
    @Null(message = "没有权限修改收藏数量", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private Integer collectNum;

    /**
     * 销售数量
     */
    @Null(message = "没有权限修改销售数量", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private Integer saleNum;

    /**
     * 评论数量
     */
    private Integer commentNum;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
