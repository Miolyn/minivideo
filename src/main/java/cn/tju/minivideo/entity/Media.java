package cn.tju.minivideo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class Media implements Serializable {
//    @TableId
    private Integer mediaId;

    private String mediaUrl;

    /**
    * 1 图片类型 2视频类型
    */
    private Integer mediaType;

    private String userId;

    /**
    * 创建时间
    */
    private LocalDateTime createdAt;

    /**
    * 更新时间
    */
    private LocalDateTime updatedAt;

    @TableLogic
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;

    public Media(String mediaUrl, Integer mediaType, String userId) {
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.userId = userId;
    }
}