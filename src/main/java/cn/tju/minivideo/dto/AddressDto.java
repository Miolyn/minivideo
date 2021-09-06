package cn.tju.minivideo.dto;

import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Integer addressId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 真实姓名
     */
    @NotBlank(message = "真实姓名不能为空", groups = {ValidationGroups.Insert.class})
    @Length(min = 1, max = 100, message = "真实姓名长度范围为1-100", groups = {ValidationGroups.Insert.class})
    private String trueName;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空", groups = {ValidationGroups.Insert.class})
    @Length(min = 1, max = 100, message = "地址长度范围为1-255", groups = {ValidationGroups.Insert.class})
    private String addr;

    /**
     * 联系方式
     */
    @NotBlank(message = "电话号码不能为空", groups = {ValidationGroups.Insert.class})
    @Length(min = 1, max = 20, message = "电话号码长度范围为1-20", groups = {ValidationGroups.Insert.class})
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String phone;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
