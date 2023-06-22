package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();

        var user = User.withUsername("sunny")
                .password("1234")
                .authorities("read")
                .build();

        userDetailsService.createUser(user);

        return userDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // 옛날에는 AuthenticationManagerBuilder를 사용해서 userDetailsService, passwordEncoder를 설정해줬어야 함.
    // 변경된 방식은 AuthenticationManager 빈 생성 시 위에서 작성했던 두 Bean을 자동으로 설정해 준다.
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
