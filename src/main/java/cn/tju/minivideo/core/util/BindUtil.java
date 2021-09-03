package cn.tju.minivideo.core.util;

import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ControllerException;
import org.springframework.validation.BindingResult;

import java.util.Objects;

public class BindUtil {
    public static void checkBindValid(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            String defaultError = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            throw new ControllerException(MsgEnums.VALIDATION_ERROR.code(), defaultError);
        }
    }
}
