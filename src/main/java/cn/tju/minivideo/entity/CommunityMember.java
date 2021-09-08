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
public class CommunityMember implements Serializable {
    private Integer communityMemberId;

    private String userId;

    private Integer communityId;

    /**
    * 状态 1正常 2社区封禁
    */
    private Integer status;

    /**
    * 权限 1普通 2社区管理员 3超级管理员 社区创建者为超管
    */
    private Integer authority;

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

    public CommunityMember(String userId, Integer communityId, Integer status, Integer authority) {
        this.userId = userId;
        this.communityId = communityId;
        this.status = status;
        this.authority = authority;
    }
}