package cn.tju.minivideo.core.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paginator implements Serializable {

    // 当前页数
    private Integer page;
    // 总页数
    private Integer pageSize;
    // 当前页含有元素
    private Integer size;
    // 含有总元素
    private Integer totalPage;

    private Object data;
}
