package cn.tju.minivideo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("cn.tju.minivideo.dao")
@EnableSwagger2
public class MinivideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinivideoApplication.class, args);
    }

}
