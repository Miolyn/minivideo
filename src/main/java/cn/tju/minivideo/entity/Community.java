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
public class Community implements Serializable {
    private Integer communityId;

    /**
    * 社区名称
    */
    private String communityName;

    /**
    * 社区简介
    */
    private String introduction;

    /**
    * 社区封面
    */
    private String avatar;

    /**
    * 创建人id
    */
    private String userId;

    /**
    * 成员数量
    */
    private Integer memberNum;

    /**
    * 帖子数量
    */
    private Integer activityNum;

    /**
    * 主标签
    */
    private Integer mainLabelId;

    /**
    * 自定义标签
    */
    private String selfLabelIds;

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