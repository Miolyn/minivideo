package cn.tju.minivideo.core.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspectAop {

    // 设置切面
    @Pointcut("execution(public * cn.tju.minivideo.controller.*Controller.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("PATH : " + request.getServletPath());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        log.info("方法的返回值 : " + ret);
    }


    //后置异常通知
    @AfterThrowing("webLog()")
    public void doAfterThrowing(JoinPoint jp) {
        log.info("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void doAfter(JoinPoint jp) {
        log.info("方法最后执行.....");
    }


//    // bindResult
//    @Around("webLog() && args(.., bindingResult)")
//    public Object aroundFunctionWithBindingResult(ProceedingJoinPoint joinPoint, BindingResult bindingResult) throws Throwable {
//        log.info("参数异常 方法环绕start.....");
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // 记录下请求内容
//        log.info("URL : " + request.getRequestURL().toString());
//        log.info("PATH : " + request.getServletPath());
//        log.info("HTTP_METHOD : " + request.getMethod());
//        log.info("IP : " + request.getRemoteAddr());
//        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//        Object o = joinPoint.proceed();
//        log.info("\r\n返回结果: " + o);
//        return o;
//    }


    // 环绕通知,环绕增强，相当于MethodInterceptor
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        log.info("方法环绕start.....");
        Object o = pjp.proceed();
        return o;
    }
}
