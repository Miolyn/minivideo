package cn.tju.minivideo.dto;
import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    /**
     * 用户id
     */
    @NotBlank(message = "用户id不能为空", groups = ValidationGroups.IdForm.class)
    private String userId;

    /**
     * 头像
     */
    @Length(min = 1, max = 255, message = "简介长度为1-255", groups = {ValidationGroups.Update.class})
    private String avatar;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = ValidationGroups.Insert.class)
    @Length(min = 6, max = 20, message = "用户名长度为6-20", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = ValidationGroups.Insert.class)
    @Length(min = 6, max = 20, message = "密码长度为6-20", groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private String password;


    /**
     * 简介
     */
    @Length(min = 1, max = 255, message = "简介长度为1-255", groups = {ValidationGroups.Update.class})
    private String introduction;

    /**
     * 个性签名
     */
    @Length(min = 1, max = 255, message = "简介长度为1-255", groups = {ValidationGroups.Update.class})
    private String signature;

    /**
     * 性别 1男 2女
     */
    @Range(max=2, min=1, message = "性别：男1，女2",groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
    private Integer sex;
}
