package com.zerock.mallapi.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zerock.mallapi.dto.TokenDTO;
import com.zerock.mallapi.util.CustomJWTException;
import com.zerock.mallapi.util.JWTUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class APIRefreshController {

    @RequestMapping("/api/member/refresh")
    public Map<String, Object> refresh(@RequestHeader("Authorization") String authHeader, @RequestBody TokenDTO tokenDTO){
      
      String refreshToken = tokenDTO.getRefreshToken();
      log.info("Controller에서 들어온 refreshToken :" + refreshToken );
      if(refreshToken == null) {
        throw new CustomJWTException("NULL_REFRASH");
      }
      
      if(authHeader == null || authHeader.length() < 7) {
        throw new CustomJWTException("INVALID_STRING");
      }
  
      String accessToken = authHeader.substring(7);
  
      //Access 토큰이 만료되지 않았다면 
      if(checkExpiredToken(accessToken) == false ) {
        return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
      }
  
      //Refresh토큰 검증 
      Map<String, Object> claims = JWTUtil.validateToken(refreshToken);
      
      log.info("refresh ... claims: " + claims);
  
      //TODO 변경 필요
      String newAccessToken = JWTUtil.generateToken(claims, 1);
  
      String newRefreshToken =  checkTime((Integer)claims.get("exp")) == true? JWTUtil.generateToken(claims, 60*24) : refreshToken;
  
      return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);
    }

    // 시간이 1시간 미만으로 남았다면
    private boolean checkTime(Integer exp) {

        // JWT exp를 날짜로 변환
        java.util.Date expDate = new java.util.Date((long) exp * (1000));

        // 현재 시간과의 차이 계산 - 밀리세컨즈
        long gap = expDate.getTime() - System.currentTimeMillis();

        // 분단위 계산
        long leftMin = gap / (1000 * 60);

        // 1시간도 안남았는지..
        return leftMin < 60;
    }

    private boolean checkExpiredToken(String token) {

        try {
            JWTUtil.validateToken(token);
        } catch (CustomJWTException ex) {
            if (ex.getMessage().equals("Expired")) {
                return true;
            }
        }
        return false;
    }

}