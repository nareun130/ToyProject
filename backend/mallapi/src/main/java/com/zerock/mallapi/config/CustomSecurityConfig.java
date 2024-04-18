package com.zerock.mallapi.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.zerock.mallapi.security.filter.JWTCheckFilter;
import com.zerock.mallapi.security.handler.APILoginFailHandler;
import com.zerock.mallapi.security.handler.APILoginSuccessHandler;
import com.zerock.mallapi.security.handler.CustomAccessDeniedHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableMethodSecurity//* @PreAuthorize, @PostAuthorize, @PreFilter, @PostFilter 를 기본으로 사용할 수 있게 해준다.
public class CustomSecurityConfig {

  // password 인코더 설정
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    log.info("------------security config--------------");// 보안 설정의 시작
    http.cors(httpSecurityCorsConfigurer -> {
      httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
    });

    // CSRF 비활성화
    http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.csrf(config -> config.disable());

    // 기본 로그인 url설정
    http.formLogin(config -> {
      config.loginPage("/api/member/login");
      config.successHandler(new APILoginSuccessHandler());
      config.failureHandler(new APILoginFailHandler());
    });

    //* UsernamePasswordAuthenticationFilter전에 JWTCheckFilter를 실행
    //* UsernamePasswordAuthenticationFilter : 스프링 시큐리티의 기본필터 -> 폼 로그인 처리 시 주로 사용
    http.addFilterBefore(new JWTCheckFilter(), UsernamePasswordAuthenticationFilter.class);
    //* 접근 제한 핸들러 붙여주기
    http.exceptionHandling(config -> {
      config.accessDeniedHandler(new CustomAccessDeniedHandler());
    });
    return http.build();

  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {

    CorsConfiguration configuration = new CorsConfiguration();

    // 모든 출처의 요청 허용
    configuration.setAllowedOriginPatterns(Arrays.asList("*"));
    // 메서드 사용 허용
    configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
    // 헤더 사용 허용
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
    // 쿠키 및 기타 인증 정보를 요청과 함께 전송 가능
    configuration.setAllowCredentials(true);

    // URL기반 CORS구성 소스 생성
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    // 모든 경로에 대해 위에서 정의된 CORS구성을 허용
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}