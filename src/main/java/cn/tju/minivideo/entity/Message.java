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
public class Message implements Serializable {
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
     * 消息类型 1系统消息，2回复我的（commentId），3收到的赞（likeMapId），4用户间消息
     */
    private Integer messageType;

    private String content;

    /**
     * 是否已读，0为未读，1为已读
     */
    private Integer isRead;

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

    public Message(String fromId, Integer fromType, String toId, Integer messageType, String content) {
        this.fromId = fromId;
        this.fromType = fromType;
        this.toId = toId;
        this.messageType = messageType;
        this.content = content;
    }
}