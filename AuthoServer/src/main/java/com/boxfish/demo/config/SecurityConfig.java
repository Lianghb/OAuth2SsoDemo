
package com.boxfish.demo.config;

import com.boxfish.demo.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by boxfish on 15/12/19.
 */

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 所有用户可访问
     */

    private final String[] permitedUrls = {"/myplugins/**", "/dist/**"};


    /**
     * USER权限可访问
     */

    private final String[] userUrls = {"/user/**"};

    /**
     * ADMIN权限可访问
     */

    private final String[] adminUrls = {"/admin/**"};


    /**
     * DBA权限可以访问
     */

    private final String[] dbaUrls = {"/dba/**"};

    @Autowired
    CustomUserDetailsService service;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.debug("configure(AuthenticationManagerBuilder auth)");
        PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
        auth.userDetailsService(service).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("configure(HttpSecurity http)");
//        http
//                .logout()
//                .logoutUrl("/logout")
//                .invalidateHttpSession(true)
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/login")
//                .loginPage("/login").permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers(permitedUrls).permitAll()
//                .anyRequest().authenticated()
//                .antMatchers(userUrls).hasRole("USER")
//                .antMatchers(adminUrls).hasRole("ADMIN")
//                .antMatchers(dbaUrls).hasRole("DBA")
//        ;
        http
                .authorizeRequests()
                .antMatchers("/static/css/**").permitAll()
                .anyRequest().fullyAuthenticated()

                .and()
                .httpBasic()
//                .formLogin().loginPage("/login")
//                .failureUrl("/login?error").permitAll().and().logout().permitAll()
        ;
    }
}
