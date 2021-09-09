package cn.tju.minivideo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDto {
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
}
