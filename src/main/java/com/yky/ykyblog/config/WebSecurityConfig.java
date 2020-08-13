package com.yky.ykyblog.config;

import com.yky.ykyblog.service.security.CustomUserServiceImpl;
import com.yky.ykyblog.utils.MD5PasswordEncoder;
import com.yky.ykyblog.utils.MD5Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: yky
 * @CreateTime: 2020-08-06
 * @Description: Security配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService(){
        return new CustomUserServiceImpl();
    }

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService())
                //启动MD5加密
                .passwordEncoder(new MD5PasswordEncoder());
    }

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/index","/aboutme","/archives","/categories","/friendlylink","/tags","/update").permitAll()
                .antMatchers("/editor","/user").hasAnyRole("USER")
                .antMatchers("/ali","/mylove").hasAnyRole("ADMIN")
                .antMatchers("/superadmin","/myheart","/today","/yesterday").hasAnyRole("SUPERADMIN")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error").defaultSuccessUrl("/")
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");

        http.csrf().disable();
    }
}
