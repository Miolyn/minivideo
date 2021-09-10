package cn.tju.minivideo.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private Integer messageId;

    /**
     * 发送者id
     */
    private String fromId;

    /**
     * 发送者类型 1系统，2用户，3网站超级管理员
     */
    private Integer fromType;

    /**
     * 接受者id
     */
    private String toId;

    /**
     * 发送者类型 1系统消息，2回复我的（commentId），3收到的赞（likeMapId），4用户间消息
     */
    private Integer messageType;

    private Object content;

    /**
     * 是否已读，0为未读，1为已读
     */
    private Integer isRead;

}
