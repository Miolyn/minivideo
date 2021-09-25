package cn.tju.minivideo.entity;

import java.io.Serializable;
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
public class User implements Serializable {
    private Integer id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 状态 1正常 2封号
     */
    private Integer status;

    /**
     * 关注的数量
     */
    private Integer followNum;

    /**
     * 性别 1男2女
     */
    private Integer sex;

    /**
     * 粉丝的数量
     */
    private Integer fansNum;

    private Integer isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 视频获得的点赞量总数
     */
    private Integer likeNum;

    private static final long serialVersionUID = 1L;
}