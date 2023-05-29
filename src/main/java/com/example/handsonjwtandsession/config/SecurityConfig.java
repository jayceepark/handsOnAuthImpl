package com.example.handsonjwtandsession.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests().antMatchers("/").permitAll();                  //아무나 접근 가능한 주소
        httpSecurity.authorizeRequests().antMatchers("/main").authenticated();          //main 이하 url은 로그인한 사용자만 접근
        httpSecurity.authorizeRequests().antMatchers("/member/**").authenticated();     //member 이하 url은 로그인한 사용자만 접근 가능
        httpSecurity.authorizeRequests().antMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN");   //manager 이하 url은 manager, admin 권한 사용자만 접근 가능
        httpSecurity.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN");               //admin 이하 url은 manager, admin 권한 사용자만 접근 가능

        httpSecurity.csrf().disable();

        httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/main",true);
        httpSecurity.formLogin().loginProcessingUrl("/loginAction").defaultSuccessUrl("/main",true); //로그인 액션 프로세스 반드시 post, 그리고 성공시 url
        httpSecurity.exceptionHandling().accessDeniedPage("/accessDenied");//권한 없을 때 접근했을 경우
        httpSecurity.logout().logoutUrl("/logout").logoutSuccessUrl("/");   //로그아웃 URL
        httpSecurity.userDetailsService(userDetailsService);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
