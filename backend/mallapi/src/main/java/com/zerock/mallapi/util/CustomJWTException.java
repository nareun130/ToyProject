package com.zerock.mallapi.util;

//JWTUtil 예외 클래스
public class CustomJWTException extends RuntimeException{
    public CustomJWTException(String msg){
        super(msg);
    }
}
