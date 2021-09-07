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
public class VideoGoodsRecommend implements Serializable {
    private Integer videoGoodsRecommendId;

    /**
    * 用户id
    */
    private String userId;

    /**
    * 视频id
    */
    private Integer videoId;

    /**
    * 商品id
    */
    private Integer goodsId;

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