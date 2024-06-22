/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnt.configs;

import com.dnt.components.JwtService;
import com.dnt.filters.CustomAccessDeniedHandler;
import com.dnt.filters.JwtAuthenticationTokenFilter;
import com.dnt.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author DikamonTu
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.dnt.controllers",
    "com.dnt.repository",
    "com.dnt.service",
    "com.dnt.components"})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }   
    
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/nguoithue/").permitAll();
        http.authorizeRequests().antMatchers("/api/nguoithue/**").permitAll();
        http.authorizeRequests().antMatchers("/api/chutro/").permitAll();
        http.authorizeRequests().antMatchers("/api/chutro/**").permitAll();
        http.authorizeRequests().antMatchers("/api/tin/").permitAll();
        http.authorizeRequests().antMatchers("/api/tin/**").permitAll();
        http.authorizeRequests().antMatchers("/api/follow/").permitAll();
        http.authorizeRequests().antMatchers("/api/follow/**").permitAll();
        http.authorizeRequests().antMatchers("/api/taikhoan/").permitAll();
        http.authorizeRequests().antMatchers("/api/taikhoan/**").permitAll();
        http.authorizeRequests().antMatchers("/api/thanhpho/").permitAll();
        http.authorizeRequests().antMatchers("/api/thanhpho/**").permitAll();
        http.authorizeRequests().antMatchers("/api/quanhuyen/").permitAll();
        http.authorizeRequests().antMatchers("/api/quanhuyen/**").permitAll();
        http.authorizeRequests().antMatchers("/api/kinhdovido/").permitAll();
        http.authorizeRequests().antMatchers("/api/kinhdovido/**").permitAll();
        http.authorizeRequests().antMatchers("/api/hinhanhtro/").permitAll();
        http.authorizeRequests().antMatchers("/api/hinhanhtro/**").permitAll();
        http.authorizeRequests().antMatchers("/api/phongtro/").permitAll();
        http.authorizeRequests().antMatchers("/api/phongtro/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**/comment/").permitAll();
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_NGUOITHUE') or hasRole('ROLE_CHUTRO')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_NGUOITHUE') or hasRole('ROLE_CHUTRO')").and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}
