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
public class Comment implements Serializable {
    private Integer commentId;

    private String content;

    /**
    * 评论者id，用户
    */
    private String fromId;

    /**
    * 评论对象id
    */
    private Integer toId;

    /**
    * 1评论视频，2回复评论，3回复帖子
    */
    private Integer commentType;

    /**
    * 点赞数量
    */
    private Integer likeNum;

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