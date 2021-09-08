package cn.tju.minivideo.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelDto {
    private Integer labelId;

    private String labelName;

    /**
     * 标签类型 1up主标签 2视频分类标签 3系统标签 4自定义标签
     */
    private Integer labelType;
}
