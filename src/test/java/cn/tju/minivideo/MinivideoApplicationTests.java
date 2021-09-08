package cn.tju.minivideo;

import cn.tju.minivideo.core.config.JwtConfig;
import cn.tju.minivideo.core.config.UploadConfig;
import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.util.FileUtil;
import cn.tju.minivideo.core.util.JwtUtil;
import cn.tju.minivideo.core.util.VideoUtils;
import cn.tju.minivideo.dao.DynamicMapper;
import cn.tju.minivideo.dao.StudentMapper;
import cn.tju.minivideo.dao.UserMapper;
import cn.tju.minivideo.dao.VideoGoodsRecommendMapper;
import cn.tju.minivideo.entity.Dynamic;
import cn.tju.minivideo.entity.Student;
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.entity.VideoGoodsRecommend;
import cn.tju.minivideo.service.RedisService;
import cn.tju.minivideo.service.StudentService;
import cn.tju.minivideo.service.UserService;
import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

@SpringBootTest
class MinivideoApplicationTests {
    @Test
    void contextLoads() {
    }

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private VideoGoodsRecommendMapper videoGoodsRecommendMapper;

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
        userMapper.deleteById("12345");
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
        System.out.println(Constants.isGoodsTypeValid(1));

    }

    @Test
    void testVideoGoodsRecommendService() throws IllegalAccessException {
        VideoGoodsRecommend record= new VideoGoodsRecommend();
        record.setVideoGoodsRecommendId(1);
        record.setVideoId(114514);
        record.setGoodsId(1919810);
        
    }
}
