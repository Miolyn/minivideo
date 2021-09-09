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
public class Topic implements Serializable {
    private Integer topicId;

    /**
     * 话题名称
     */
    private String topicName;

    /**
     * 第一个创建话题的人
     */
    private String userId;

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

    public Topic(String topicName, String userId) {
        this.topicName = topicName;
        this.userId = userId;
    }
}