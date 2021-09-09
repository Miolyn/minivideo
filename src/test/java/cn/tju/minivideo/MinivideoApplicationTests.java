package cn.tju.minivideo;

import cn.tju.minivideo.core.config.JwtConfig;
import cn.tju.minivideo.core.config.UploadConfig;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.util.FileUtil;
import cn.tju.minivideo.core.util.JwtUtil;
import cn.tju.minivideo.core.util.StringUtil;
import cn.tju.minivideo.dao.DynamicMapper;
import cn.tju.minivideo.dao.UserMapper;
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.service.RedisService;
import cn.tju.minivideo.service.UserService;
import com.auth0.jwt.interfaces.Claim;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MinivideoApplicationTests {

    @Test
    void contextLoads() {
    }



    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @Test
    void test(){
        User user = new User();
        user.setUserId("12345");
        user.setUsername("username1");
        user.setAvatar("123");
        userMapper.insert(user);
        System.out.println(JwtConfig.secret);
        System.out.println(JwtConfig.header);
        user = new User();
        user.setUserId("123");
        String token = JwtUtil.createToken(user);
        System.out.println(token);
        Map<String, Claim> map = JwtUtil.verifyToken(token);
        Claim claim = map.get(JwtConfig.UserId);
        Claim claim2 = map.get("exp");
        System.out.println(claim2.as(Date.class));
        String userId = claim.asString();
        System.out.println(userId);
    }

    @Test
    void testInsert(){
        User user = new User();
        user.setUserId("12345");
        user.setUsername("username1");
        user.setAvatar("123");
        userMapper.insert(user);
    }
    @Test
    void testDelete(){
        User user = new User();
        user.setUserId("12345");
        user.setUsername("username1");
//        userMapper.deleteById("12345");
    }
    @Test
    void testSelect(){
        User user = userMapper.selectByPrimaryKey("12345");
        System.out.println(user);
    }

    @Test
    void testPath(){
        System.out.println(UploadConfig.imgPath);
        System.out.println(JwtConfig.header);
        String path = FileUtil.uploadGetPath(UploadConfig.imgPath);
        System.out.println(path);
        System.out.println(FileUtil.uploadGetNewName("hello.jpg"));
    }
    @Resource
    private RedisService redisService;

    @Test
    void testRedis(){
        User user = new User();
        user.setUserId("123456");
        user.setUsername("username");
        redisService.set(user.getUserId(), user);
        User ret = (User) redisService.get(user.getUserId());
        System.out.println(ret);
    }

    @Test
    void testVideoTime(){
//        File file = new File("src/main/resources/static/videos/4.mp4");
//        int duration = VideoUtils.getVideoDuration(file);
//        System.out.println(duration);

    }

    @Autowired
    private DynamicMapper dynamicMapper;

    @Test
    void testField() throws IllegalAccessException {

    }

    @Test
    void testTopic(){
        String content = "#哈哈a###这是一个#好####哈哈a##哈#啊圣诞节疯狂#奥斯卡级代付款##as的开发#";
        List<String> topicSet = StringUtil.getTopicList(content);
        for (String s : topicSet) {
            System.out.println(s);
        }
    }
}
