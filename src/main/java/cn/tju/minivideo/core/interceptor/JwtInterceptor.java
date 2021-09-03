package cn.tju.minivideo.core.interceptor;

import cn.tju.minivideo.core.annotation.AuthRequired;
import cn.tju.minivideo.core.config.JwtConfig;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.JwtException;
import cn.tju.minivideo.core.util.JwtUtil;
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<User> tl = new ThreadLocal<>();

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader(JwtConfig.header);// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(AuthRequired.class)) {
            AuthRequired authRequired = method.getAnnotation(AuthRequired.class);
            if (authRequired.required()) {
                // 执行认证
                if (token == null) {
                    throw new JwtException(MsgEnums.TOKEN_NOT_FOUND.code(), MsgEnums.TOKEN_NOT_FOUND.desc());
                }
                // 获取 token 中的 user id
                Map<String, Claim> map = JwtUtil.verifyToken(token);
                Claim claim = map.get(JwtConfig.UserId);
                String userId = claim.asString();
                log.info(userId);
                User user = userService.getUserByUserIdWithRedis(userId);
//                User user = userService.selectByPrimaryKey(userId);
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                tl.set(user);
                return true;
            } else if (token != null){
                // 获取 token 中的 user id
                Map<String, Claim> map = JwtUtil.verifyToken(token);
                Claim claim = map.get(JwtConfig.UserId);
                String userId = claim.asString();
                log.info(userId);
                User user = userService.getUserByUserIdWithRedis(userId);
//                User user = userService.selectByPrimaryKey(userId);
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                tl.set(user);
                return true;
            } else{
                tl.set(null);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        //程序运行结束之后，删除线程
        tl.remove();
    }

    public static User getUser() {
        return tl.get();
    }
}
