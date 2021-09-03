package cn.tju.minivideo.dto;


import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    /**
     * 评论id
     */
    private Integer commentId;


    @Length(min = 1, max = 255, message = "评论长度范围1-255", groups = {ValidationGroups.Insert.class})
    private String content;

    /**
     * 评论者id，用户
     */
    private String fromId;

    /**
     * 评论对象id
     */
    @NotNull(message = "评论对象id不能为空", groups = {ValidationGroups.Insert.class})
    private Integer toId;

    /**
     * 1评论视频，2回复评论，3回复帖子
     */
    @NotNull(message = "评论对象类型不能为空", groups = {ValidationGroups.Insert.class})
    @Range(min = 1, max = 3, message = "评论对象类型范围1-3，1评论视频，2回复评论，3回复帖子")
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
     * 回复的评论
     */
    private List<CommentDto> replays;
}
