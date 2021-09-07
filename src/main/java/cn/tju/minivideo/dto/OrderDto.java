package cn.tju.minivideo.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer orderId;

    private BigDecimal payPrice;

    /**
     * 1待付款，2付款完成，3待发货，4待收货，5确认收货
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
}
