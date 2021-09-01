package cn.tju.minivideo.core.exception;

import cn.tju.minivideo.core.constants.MsgEnums;

public class ControllerException extends BaseException {
    public ControllerException(){

    }
    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(Integer code, String message) {
        super(code, message);
    }
    public ControllerException(MsgEnums message) {
        super(message.code(), message.desc());
    }
}
