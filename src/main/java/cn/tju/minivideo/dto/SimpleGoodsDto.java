package cn.tju.minivideo.dto;

import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

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
public class SimpleGoodsDto {

    private Integer goodsId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品图标
     */
    private String avatar;

    /**
     * 商品类别
     */
    private Integer goodsType;

    /**
     * 点赞数量
     */
    private Integer likeNum;

    /**
     * 收藏数量
     */
    private Integer collectNum;

    /**
     * 销售数量
     */
    private Integer saleNum;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
