package com.zerock.mallapi.util;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTUtil {

  private static String key = "1234567890123456789012345678901234567890";

  public static String generateToken(Map<String, Object> valueMap, int min) {

    SecretKey key = null;

    try{
      key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));// HMAC SHA키를 사용하여 시크릿키 생성
    
    }catch(Exception e){
        throw new RuntimeException(e.getMessage());
    }

    String jwtStr = Jwts.builder()
            .setHeader(Map.of("typ","JWT"))
            .setClaims(valueMap)//토큰에 포함할 정보를 담고있는 맵
            .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
            .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
            .signWith(key)
            .compact();

    return jwtStr;
  }

  //토큰 검증하고 토큰의 클레임(토큰에 포함된 정보) 반환
  public static Map<String, Object> validateToken(String token) {

    Map<String, Object> claim = null;
    
    try{

      SecretKey key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));

      claim = Jwts.parserBuilder()
              .setSigningKey(key)
              .build()
              .parseClaimsJws(token) // 파싱 및 검증, 실패 시 에러
              .getBody();
              
    }catch(MalformedJwtException malformedJwtException){
        throw new CustomJWTException("MalFormed");//형식 오류
    }catch(ExpiredJwtException expiredJwtException){
        throw new CustomJWTException("Expired");//만료
    }catch(InvalidClaimException invalidClaimException){
        throw new CustomJWTException("Invalid");//클레임 오류
    }catch(JwtException jwtException){
        throw new CustomJWTException("JWTError");
    }catch(Exception e){
        throw new CustomJWTException("Error");
    }
    return claim;
  }

}
