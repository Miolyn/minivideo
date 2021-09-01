package cn.tju.minivideo.core.exception;

public class JwtException extends BaseException{
    public JwtException(){

    }
    public JwtException(String message) {
        super(message);
    }

    public JwtException(Integer code, String message) {
        super(code, message);
    }
}
