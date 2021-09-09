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
public class Activity implements Serializable {
    private Integer activityId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 社区id
     */
    private Integer communityId;

    /**
     * 话题ids
     */
    private String topicIds;

    /**
     * 点赞数量
     */
    private Integer likeNum;

    /**
     * 收藏数量
     */
    private Integer collectNum;

    /**
     * 评论数量
     */
    private Integer commentNum;

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
     * 帖子内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}