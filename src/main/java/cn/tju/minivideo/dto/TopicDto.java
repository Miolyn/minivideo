package cn.tju.minivideo.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {
    private Integer topicId;

    /**
     * 话题名称
     */
    private String topicName;
}
