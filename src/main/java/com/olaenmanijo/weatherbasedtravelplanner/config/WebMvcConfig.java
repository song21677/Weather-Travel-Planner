package com.olaenmanijo.weatherbasedtravelplanner.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.olaenmanijo.weatherbasedtravelplanner.domain.member.interceptor.LoggerInterceptor;
import com.olaenmanijo.weatherbasedtravelplanner.domain.member.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		/*-
		   addInterceptor()
		      애플리케이션 내의 모든 페이지(URI)에 접근할 때 LoginCheckInterceptor의 
		   preHandle( )이 작동하는데 excludePathPatterns( )를 이용해서
		      로그인 페이지와 로그인/로그아웃 URL는 인터셉터 실행에서 제외시킴
		      만약 로그인/로그아웃 관련 URI를 호출했을 때 인터셉터가 작동하게 되면, 
		   LoginCheckInterceptor의 preHandle( )에 의해 계속해서 
		      로그인 페이지를 호출하는 무한 루프에 빠지게 됨
		
		*/
        registry.addInterceptor(new LoggerInterceptor())
                .excludePathPatterns("/css/**", "/images/**", "/js/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/**/*.do", "/communities/**", "/plan/**")
                .excludePathPatterns("/log*");
    }
}
