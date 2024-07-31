package com.Alssongdalsong.common.configs;

import com.Alssongdalsong.common.auth.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity // security를 위한 소스코드다 라는 명시
@EnableGlobalMethodSecurity(prePostEnabled = true) // pre : 사전검증 , post : 사후검증
@Configuration
public class SecurityConfigs {
    private final JwtAuthFilter jwtAuthFilter;

    @Autowired
    public SecurityConfigs(JwtAuthFilter jwtAuthFilter){
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .cors().and() // CORS 활성화
                .httpBasic().disable()
                .authorizeRequests()

                    // (antMatchers) 예외 url 넣기 : 로그인하지 않아도 들어갈 수 있는
                    .antMatchers("/member/create", "/", "/doLogin","/refresh-token")
                    .permitAll()
                .anyRequest().authenticated()
                .and()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // STATELESS : 상태가 없는.
                .and()

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // 소스가 너무 길어서 따로 파일구분

                .build();
    }
}
