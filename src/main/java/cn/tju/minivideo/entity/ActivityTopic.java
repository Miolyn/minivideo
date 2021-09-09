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
public class ActivityTopic implements Serializable {
    private Integer activityTopicId;

    /**
    * 话题id
    */
    private Integer topicId;

    /**
    * 帖子id
    */
    private Integer activityId;

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

    public ActivityTopic(Integer topicId, Integer activityId) {
        this.topicId = topicId;
        this.activityId = activityId;
    }
}