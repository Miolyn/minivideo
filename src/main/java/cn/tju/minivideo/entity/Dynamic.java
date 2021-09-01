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
public class Dynamic implements Serializable {
    private Integer dynamicId;

    /**
    * 用户id
    */
    private String userId;

    /**
    * 内容 若为自动动态  则内容则填写视频id
    */
    private String content;

    /**
    * 动态类型 1普通动态 2自动动态（up主发了视频）
    */
    private Integer dynamicType;

    private Integer likeNum;

    private Integer commentNum;

    private Integer collectNum;

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