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
public class Order implements Serializable {
    private Integer orderId;

    private BigDecimal payPrice;

    /**
    * 1待付款，2待发货，3待收货，4确认收货, 5订单完成
    */
    private Integer status;

    private String userId;

    /**
    * 地址id
    */
    private Integer addressId;

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
}