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
public class Relation implements Serializable {
    private Integer relationId;

    private String fromId;

    private String toId;

    /**
    * 1 关注
    */
    private Integer relationType;

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

    public Relation(String fromId, String toId, Integer relationType) {
        this.fromId = fromId;
        this.toId = toId;
        this.relationType = relationType;
    }
}