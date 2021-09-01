package cn.tju.minivideo.controller;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
import cn.tju.minivideo.core.util.Results;
import cn.tju.minivideo.entity.Student;
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.service.RedisService;
import cn.tju.minivideo.service.StudentService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class testController {

    private static final Logger logger = LogManager.getLogger(testController.class);

    @AuthRequired
    @GetMapping("hello")
    public String hello() {
        logger.info("info hello api");
        return "hello";
    }

    @ApiOperation("测试API")
    @RequestMapping("test")
    public Student test() {
        throw new ServiceException(MsgEnums.FAIL.code(), MsgEnums.FAIL.desc());
//        return new Student(2, "name", 14);
    }

    @Autowired
    private StudentService studentService;



    @GetMapping("page")
    public Result getStudentByPage(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
        PageInfo<Student> students = studentService.queryByPage(pageNo, pageSize);
        return Results.OkWithData(students);
    }


    @Resource
    private RedisService redisService;

    @PostMapping("setRedis")
    public Result setRedis(@RequestParam(value = "name") String name){
        redisService.set("name", name);
        return Results.Ok();
    }

    @GetMapping("getRedis")
    public Result getRedis(){
        String name = (String) redisService.get("name");
        return Results.OkWithData(name);
    }

    @PostMapping("/re")
    public Result re(@RequestBody User user){
        return Results.OkWithData(user);
    }
}
