package cn.tju.minivideo.core.util;

import cn.tju.minivideo.core.base.Result;
import cn.tju.minivideo.core.constants.MsgEnums;

public class Results {

    public static Result Ok() {
        return new Result(MsgEnums.SUCCESS);
    }

    public static Result Fail() {
        return new Result(MsgEnums.FAIL);
    }

    public static Result FailWithMsg(MsgEnums msgEnums) {
        return new Result(msgEnums.code(), msgEnums.desc(), "", null);
    }

    public static Result FailWithMsgAndDesc(MsgEnums msgEnums, String desc) {
        return new Result(msgEnums.code(), desc, "", null);
    }

    public static Result OkWithData(Object data) {
        return new Result(MsgEnums.SUCCESS, data);
    }

    public static Result OkWithToken(String token) {
        return new Result(MsgEnums.SUCCESS, token);
    }
}
