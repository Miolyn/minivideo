package cn.tju.minivideo.core.config;


import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ControllerException;
import cn.tju.minivideo.core.exception.JwtException;
import cn.tju.minivideo.core.exception.ServiceException;
import cn.tju.minivideo.core.util.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
// 使用该注解表示开启了全局异常的捕获
@RestControllerAdvice
public class GlobalExceptionConfig {

    @ExceptionHandler(ServiceException.class)
    public Result handlerServiceException(ServiceException e){
        Result result = new Result(e.getCode(), e.getMessage(), "", null);
        log.info("ServiceException,code:" + String.valueOf(e.getCode()) + ", Msg:" + e.getMessage());
        return result;
    }

    @ExceptionHandler(JwtException.class)
    public Result handlerJwtException(JwtException e){
        Result result = new Result(e.getCode(), e.getMessage(), "", null);
        log.info("JwtException,code:" + String.valueOf(e.getCode()) + ", Msg:" + e.getMessage());
        return result;
    }

    @ExceptionHandler(ControllerException.class)
    public Result handlerControllerException(ControllerException e){
        Result result = new Result(e.getCode(), e.getMessage(), "", null);
        log.info("ControllerException,code:" + String.valueOf(e.getCode()) + ", Msg:" + e.getMessage());
        return result;
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public Result handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();
        //返回第一个错误的信息
        String defaultMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        String code = bindingResult.getFieldError().getCode();
        String field = bindingResult.getFieldError().getField();
        log.info("参数验证错误，错误信息code: {}, 错误field: {}, 错误message: {}", code, field, defaultMessage);
        return Results.FailWithMsgAndDesc(MsgEnums.VALIDATION_ERROR, defaultMessage);
    }

    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){
        log.info("exception error: {}", e.getMessage());
        return Results.FailWithMsg(MsgEnums.INTERNAL_ERROR);
    }
}
