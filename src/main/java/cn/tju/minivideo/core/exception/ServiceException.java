package cn.tju.minivideo.core.exception;

import cn.tju.minivideo.core.constants.MsgEnums;

public class ServiceException extends BaseException {
    private static final long serialVersionUID = 6058294324031642376L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(MsgEnums msg) {
        super(msg.code(), msg.desc());
    }

    public ServiceException(Integer code, String message) {
        super(code, message);
    }
}
