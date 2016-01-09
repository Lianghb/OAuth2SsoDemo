
package com.boxfish.lhb.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by boxfish on 15/12/19.
 */

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableOAuth2Sso
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 所有用户可访问
     */

    private final String[] permitedUrls = {"/myplugins/**", "/dist/**", "/login"};


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


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("configure(HttpSecurity http)");
        http
                .antMatcher("/**")
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .antMatchers(permitedUrls).permitAll()
                .antMatchers(userUrls).access("hasRole('ROLE_USER')")
                .antMatchers(dbaUrls).access("hasRole('ROLE_DBA')")
                .antMatchers(adminUrls).access("hasRole('ROLE_ADMIN')")
        ;
    }
}
