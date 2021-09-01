package cn.tju.minivideo.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {
    /**
     * 新增自动填充方法
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
//        LocalDateTime now = LocalDateTime.now();
//        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, now);
//        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, now);
//        this.strictInsertFill(metaObject, "isDeleted", Integer.class, 0);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
//        this.strictUpdateFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
    }
}
