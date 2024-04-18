package com.zerock.mallapi.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.zerock.mallapi.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {// *OncePerRequestFilter : HttpRequest의 한 번의 요청에 대해 한 번만 실행하는
                                                          // filter

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // preflight요청 체크 x
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        String path = request.getRequestURI();
        log.info("check uri.............." + path);

        // api/member/ 경로의 호출 체크x
        if (path.startsWith("/api/member/")) {
            return true;
        }

        // 이미지 조회 경로 체크 x
        if (path.startsWith("/api/products/view/")) {
            return true;
        }

        // 나머지는 다 체크
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("----------------JWTCheckFilter------------------");

        try {
            String authHeaderStr = request.getHeader("Authorization");

            String accessToken = authHeaderStr.substring(7);// * Bearer(공백) ->7 글자 자름
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);

            log.info("JWT claims : " + claims);

            filterChain.doFilter(request, response);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("JWT Check Error............");
            log.error(e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }
    }

}
