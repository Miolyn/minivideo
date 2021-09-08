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
public class OrderGoods implements Serializable {
    private Integer orderGoodsId;

    /**
    * 订单号
    */
    private Integer orderId;

    /**
    * 商品id
    */
    private Integer goodsId;

    /**
    * 该商品总价
    */
    private BigDecimal price;

    /**
    * 数量
    */
    private Integer number;

    /**
    * 1正常
    */
    private Integer status;

    /**
    * 创建时间
    */
    private LocalDateTime createdAt;

    /**
    * 更新时间
    */
    private LocalDateTime updatedAt;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;

    public OrderGoods(Integer orderId, Integer goodsId, BigDecimal price, Integer number, Integer status) {
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.price = price;
        this.number = number;
        this.status = status;
    }
}