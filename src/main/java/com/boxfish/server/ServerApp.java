package com.boxfish.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by boxfish on 15/12/14.
 */

@SpringBootApplication
@RestController
public class ServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ServerApp.class, args);
    }

    public static final String RESOURCE_ID = "test_resource";


    /**
     * 资源服务器配置
     */
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
        private Logger logger = LoggerFactory.getLogger(getClass());

        @Override
        public void configure(HttpSecurity http) throws Exception {
            logger.debug("configure(HttpSecurity http)");
            System.err.println("rrrrrrrrrrrrrrrr----------HttpSecurity:" + http);
            http
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                    .and()
//                    .requestMatchers()
//                    .antMatchers("/photos/**", "/oauth/users/**", "/oauth/clients/**", "/me")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/me").access("#oauth2.hasScope('read')")
//                    .antMatchers("/photos").access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))")

            ;
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID);
        }
    }


    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


        //        @Autowired
//        private TokenStore tokenStore;
//
//        @Autowired
//        private UserApprovalHandler userApprovalHandler;
//
        @Autowired
//        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("client1") //客户端id
                    .secret("123456") //客户端密码
                    .resourceIds(RESOURCE_ID) //资源id
                    .accessTokenValiditySeconds(60) //token有效时间1分钟
                    .refreshTokenValiditySeconds(60) //重新获取token有效时间1分钟
                    .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//                    .autoApprove(true) //自动授权
//                    .autoApprove("read") //自动授权，read的权限
//                    .scopes("read", "write", "trust")
                    .scopes("openid", "read").autoApprove(true)
                    .redirectUris("http://localhost:8081/client1/")
//                    .redirectUris("http://localhost:8080/client/hello")
                    .and()
                    .withClient("client2")
                    .secret("client2")
                    .resourceIds(RESOURCE_ID)
                    .accessTokenValiditySeconds(60)
                    .refreshTokenValiditySeconds(60)
                    .authorities("ROLE_CLIENT")
                    .authorizedGrantTypes("authorization_code", "refresh_token")
                    .scopes("read")
                    .redirectUris("http://localhost:8082/client2")
            ;
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManager)  //添加密码认证授权支持，一般web应用不需要，测试用
//                    .tokenStore(new InMemoryTokenStore()) //默认就是内存存储
//                    .approvalStoreDisabled() //不会出现授权多选框的页面，只有授权和禁用2项
                    .pathMapping("/oauth/authorize", "/oauth/authorize") // Authorization endpoint
                    .pathMapping("/oauth/token", "/oauth/token") //token endpoint
                    .pathMapping("/oauth/confirm_access", "/oauth/confirm_access") //user posts approval for grants here
                    .pathMapping("/oauth/error", "/oauth/error") //used to render errors in the authorization server
                    .pathMapping("/oauth/check_token", "/oauth/check_token") //used by Resource Servers to decode access tokens)
                    .pathMapping("/oauth/token_key", "/oauth/token_key") // exposes public key for token verification if using JWT tokens
            ;

        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            super.configure(security);
        }
    }


    @RequestMapping("/oauth/user")
    public Object getUserInfo(OAuth2Authentication authentication) throws Exception {
        Map result = new HashMap<>(1);
        result.put("aaa", "haha");
        return result;
    }

    @RequestMapping("/protected")
    public Object getMe() {
        return "access protected resource";
    }

}