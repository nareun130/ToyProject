package com.nareun.mallapikotlin.config

import com.nareun.mallapikotlin.controller.formatter.LocalDateFormatter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.naming.ldap.Control

@Configuration
class CustomServletConfig : WebMvcConfigurer {

    //LocalDateFormatter를 사용하기 위한 설정 메서드
    override fun addFormatters(registry: FormatterRegistry) {
        registry.addFormatter(LocalDateFormatter())
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            //HEAD: 특정 리소스에 대한 헤더 정보 요청, 리소스의 유효성 검사, 변경 여부 확인, 리소스의 크기 알아내기
            //OPTIONS: 서버의 특정 URL에 대해 수행 가능한 HTTP메소드를 물어보기 위해 사용
            .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS")
            .maxAge(300)
            .allowedHeaders("Authorization", "Cache-Control", "Content-Type")
    }
}