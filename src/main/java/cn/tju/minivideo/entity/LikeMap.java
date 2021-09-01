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
public class LikeMap implements Serializable {
    private Integer likeMapId;

    /**
    * 点赞人id
    */
    private String fromId;

    /**
    * 点赞对象id
    */
    private Integer toId;

    /**
    * 点赞对象类型 1点赞视频 2点赞帖子 3点赞弹幕 4点赞商品
    */
    private Integer likeType;

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