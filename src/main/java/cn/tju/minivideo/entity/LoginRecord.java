package cn.tju.minivideo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class LoginRecord implements Serializable {
    private Integer loginRecordId;

    private String userId;

    private String token;

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

    public LoginRecord(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}