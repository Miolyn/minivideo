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
public class History implements Serializable {
    private Integer historyId;

    private String userId;

    /**
    * 阅读对象id
    */
    private Integer itemId;

    /**
    * 对象类型 1视频，2帖子，3商品
    */
    private Integer itemType;

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

    public History(String userId, Integer itemId, Integer itemType) {
        this.userId = userId;
        this.itemId = itemId;
        this.itemType = itemType;
    }
}