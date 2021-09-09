package cn.tju.minivideo.dto;

import cn.tju.minivideo.dto.validationGroup.ValidationGroups;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {
    @Null(message = "没有权限修改帖子id", groups = {ValidationGroups.Insert.class})
    private Integer activityId;

    /**
     * 用户id
     */
    @Null(message = "没有权限修改用户id", groups = {ValidationGroups.Insert.class})
    private String userId;

    /**
     * 社区id
     */
    @NotNull(message = "社区id不能为空", groups = {ValidationGroups.Insert.class})
    private Integer communityId;

    /**
     * 帖子内容
     */
    @NotEmpty(message = "帖子内容不能为空", groups = {ValidationGroups.Insert.class})
    private String content;


    /**
     * 点赞数量
     */
    @Null(message = "没有权限修改点赞数量", groups = {ValidationGroups.Insert.class})
    private Integer likeNum;

    /**
     * 收藏数量
     */
    @Null(message = "没有权限修改收藏数量", groups = {ValidationGroups.Insert.class})
    private Integer collectNum;

    /**
     * 评论数量
     */
    @Null(message = "没有权限修改评论数量", groups = {ValidationGroups.Insert.class})
    private Integer commentNum;

    /**
     * 创建时间
     */
    @Null(message = "没有权限修改发布日期", groups = {ValidationGroups.Insert.class})
    private LocalDateTime createdAt;

}
