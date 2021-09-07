package cn.tju.minivideo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
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
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品图标
     */
    private String avatar;

    /**
     * 商品类别
     */
    private Integer goodsType;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 多张图片的url
     */
    private String imgs;

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

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    private Integer isDeleted;

    /**
     * 内容，可以用markdown表示
     */
    private String content;

    private static final long serialVersionUID = 1L;
}