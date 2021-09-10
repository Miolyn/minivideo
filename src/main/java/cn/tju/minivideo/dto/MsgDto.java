package cn.tju.minivideo.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 主要用于系统通告通知，如收到回复我的，收到的赞
public class MsgDto {
    // 来自的用户id
    String fromUserId;
    // 动作对象的id，动作类型由外围的message_type区分是回复类型还是点赞类型的动作
    // 比如若是点赞则放likeMap的id，回复则放回复的对象的id，如回复视频则放视频id，回复帖子则放帖子id，
    Integer itemId;
    // 1回复/点赞视频，2回复/点赞帖子，3回复/点赞商品，4回复评论，5回复动态
    Integer itemType;
    // 若是点赞则无内容，若是评论则放评论的内容
    String content;
}
