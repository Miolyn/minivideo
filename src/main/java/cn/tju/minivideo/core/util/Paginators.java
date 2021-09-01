package cn.tju.minivideo.core.util;

import cn.tju.minivideo.core.base.Paginator;
import com.github.pagehelper.PageInfo;

public class Paginators {
    public static <T> Paginator paginator(PageInfo<T> pageInfo, Object data){
        return new Paginator(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getSize(), pageInfo.getPages(), data);
    }
}
