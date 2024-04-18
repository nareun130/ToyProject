package com.zerock.mallapi.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//* 접근 제한 상황에 대한 예외 메시지를 전달해주기 위한 클래스
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
            Gson gson = new Gson();

            String jsonStr = gson.toJson(Map.of("error","ERROR_ACCESSDENIED"));

            response.setContentType("application.json");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonStr);
            printWriter.close();
    }
}
