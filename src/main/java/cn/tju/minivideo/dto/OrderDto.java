package cn.tju.minivideo.dto;

import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @NotNull(message = "订单id不能为空", groups = ValidationGroups.IdForm.class)
    private Integer orderId;

    private BigDecimal payPrice;

    /**
     * 1待付款，2待发货，3待收货，4确认收货, 5订单完成
     */
    @Null(message = "不能修改状态", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private Integer status;

    private String userId;

    /**
     * 地址id
     */
    @NotNull(message = "地址id不能为空", groups = {ValidationGroups.Insert.class})
    private Integer addressId;

    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空", groups = {ValidationGroups.Insert.class})
    private Integer goodsId;

    /**
     * 商品id
     */
    @NotNull(message = "商品数量不能为空", groups = {ValidationGroups.Insert.class})
    @Min(value = 1, message = "最小购买数量为1", groups = {ValidationGroups.Insert.class})
    private Integer number;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
