package com.example.security.config;

import com.example.security.security.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    // AuthenticationManager => 인증 처리하는 filter로부터 인증처리를 지시받는 첫번째 클래스.
    // CustomAuthenticationProvider 클래스에게 실질적으로 인증을 맡긴 채 관리.
    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 현재는 WebSecurityConfigurerAdapter 상속을 받아 오버라이딩하지 않고 직접 SecurityFilterChain을 Bean 등록 함.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.httpBasic();
        //모든 요청에 인증 필요.
        http.authorizeRequests()
                .anyRequest().authenticated();
        // .anyRequest().permitAll() -> 인증 없이 요청 가능.

        return http.build();
    }
}
