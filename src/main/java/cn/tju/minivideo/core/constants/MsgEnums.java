package cn.tju.minivideo.core.constants;

import cn.tju.minivideo.core.base.BaseEnum;

public enum MsgEnums implements BaseEnum<Integer, String> {
    // System 0
    SUCCESS(1, "success 操作成功"),
    FAIL(-1, "fail 操作失败"),
    NO_HANDLER_FOUND(-2, "no handler found 未找到对应的接口"),
    INTERNAL_ERROR(-3, "exception error"),
    ACTION_NOT_FOUND(-4, "action not found"),
    // Jwt -100
    TOKEN_NOT_FOUND(-100, "token not found"),
    TOKEN_VERIFY_ERROR(-101, "token verify error"),
    // user -200
    USERNAME_EXIST(-200, "username exist"),
    USERNAME_OR_PASSWORD_ERROR(-201, "username or password error"),
    USER_PROFILE_ERROR(-202, "user profile information error"),
    USER_NOT_EXIST(-203, "user not exist"),
    // validation -300
    VALIDATION_ERROR(-300, "validation error"),
    // -400 upload
    UPLOAD_FILE_IS_EMPTY(-400, "file is empty"),
    UPLOAD_FORMAT_ERROR(-401, "file format error"),
    UPLOAD_SAVE_FILE_ERROR(-402, "save file error"),
    // -500
    RELATION_HAS_EXIST(-500, "relation has existed"),
    RELATION_NOT_EXIST(-501, "relation not exist"),
    // -600
    VIDEO_NOT_FOUND(-600, "video not found"),
    ;
    private Integer code;
    private String desc;
    MsgEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer code() {
        return this.code;
    }

    @Override
    public String desc() {
        return this.desc;
    }
}
