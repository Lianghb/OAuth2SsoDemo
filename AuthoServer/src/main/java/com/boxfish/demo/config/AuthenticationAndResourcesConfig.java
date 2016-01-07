package com.boxfish.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerTokenServicesConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.Assert;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

/**
 * Created by boxfish on 16/1/5.
 */

@Configuration
public class AuthenticationAndResourcesConfig {

    public static final String KEY_ALGORITHM = "RSA";
//    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";


    public static final String RESOURCE_ID = "test_resource";


//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        return new JwtAccessTokenConverter();
//    }

    /**
     * 资源服务器配置
     */
    @Configuration
    @EnableResourceServer
//    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
        private Logger logger = LoggerFactory.getLogger(getClass());

//OAuth2ClientAuthenticationProcessingFilter
//        @Autowired
//        ResourceServerTokenServices jwtTokenServices;

//        @Autowired
//        private OAuth2AuthenticationEntryPoint oauthAuthenticationEntryPoint;

        @Override
        public void configure(HttpSecurity http) throws Exception {
            logger.debug("configure(HttpSecurity http)");
            http
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/protected"
                            , "/oauth/user"
//                            ,"/oauth/resource_token"
                    ).access("#oauth2.hasScope('read')")
//                    .antMatchers("/photos").access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))")

            ;
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

            resources
                    .stateless(false)
                    .resourceId(RESOURCE_ID)
//                    .tokenServices(jwtTokenServices)
//                    .authenticationEntryPoint(oauthAuthenticationEntryPoint)
//                    .tokenStore(tokenStore)
//                    .authenticationManager(authenticationManager)
            ;
        }
    }


    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//        private AuthorizationServerTokenServices tokenServices;

        @Autowired
        private AuthenticationManager authenticationManager;

//        @Autowired
//        @Qualifier("oAuth2AuthenticationManager")
//        private AuthenticationManager authenticationManager;

//        @Bean(name = "oAuth2AuthenticationManager")
//        @Autowired
//        public AuthenticationManager authenticationManager(
////                ResourceServerTokenServices tokenService,
//                ClientDetailsService clientDetailsService) {
////            Assert.notNull(tokenService, "invalid tokenService (required not null) !");
//            Assert.notNull(clientDetailsService, "invalid clientDetailsService (required not null) !");
//            OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
//            authenticationManager.setClientDetailsService(clientDetailsService);
//            authenticationManager.setResourceId(RESOURCE_ID);
////            authenticationManager.setTokenServices(tokenService);
//            return authenticationManager;
//        }

        @Autowired
        @Qualifier("jwtTokenStore")
        private TokenStore tokenStore;

        @Bean(name = "jwtTokenStore")
        public TokenStore tokenStore() throws Exception {
            return new JwtTokenStore(accessTokenConverter());
        }

//        @Bean
//        public ResourceServerTokenServices resourceServerTokenServices(){
//            return new DefaultTokenServices();
//        }

        @Bean
        public JwtAccessTokenConverter accessTokenConverter() throws Exception {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(2048); //不同的长度产生不同的key
            KeyPair keyPair = keyPairGen.generateKeyPair();
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            jwtAccessTokenConverter.setKeyPair(keyPair);
            Map keys = jwtAccessTokenConverter.getKey();
            System.err.println("keys:" + keys);
            return jwtAccessTokenConverter;
        }

        //
//        @Autowired
//        private UserApprovalHandler userApprovalHandler;
//


        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("client1") //客户端id
                    .secret("123456") //客户端密码
                    .resourceIds(RESOURCE_ID) //资源id
                    .accessTokenValiditySeconds(600) //token有效时间1分钟
                    .refreshTokenValiditySeconds(660) //重新获取token有效时间1分钟
                    .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//                    .autoApprove(true) //自动授权
//                    .autoApprove("read") //自动授权，read的权限
//                    .scopes("read", "write", "trust")
                    .scopes("openid", "read").autoApprove(true)
                    .redirectUris("http://localhost:8081/client1")
//                    .redirectUris("http://localhost:8080/client/hello")
                    .and()
                    .withClient("client2")
                    .secret("client2")
                    .resourceIds(RESOURCE_ID)
                    .accessTokenValiditySeconds(600)
                    .refreshTokenValiditySeconds(660)
                    .authorities("ROLE_CLIENT")
                    .authorizedGrantTypes("authorization_code", "refresh_token")
                    .scopes("read").autoApprove(true) //自动授权
                    .redirectUris("http://localhost:8082/client2")

                    .and()
                    .withClient("client3")
                    .secret("client3")
                    .resourceIds(RESOURCE_ID)
                    .accessTokenValiditySeconds(600)
                    .refreshTokenValiditySeconds(660)
                    .authorities("ROLE_CLIENT")
                    .authorizedGrantTypes("authorization_code", "refresh_code")
                    .scopes("read").autoApprove(true) //自动授权
                    .redirectUris("http://localhost:8083/client3/simple")
            ;
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            Assert.notNull(authenticationManager, " invalid authenticationManager (required not null) !");
            Assert.notNull(tokenStore, "invalid tokenStore (required not null) !");
//            Assert.notNull(tokenServices, "invalid tokenServices (required not null) !");
            endpoints
//                    .tokenServices(tokenServices)
                    .authenticationManager(authenticationManager)  //添加密码认证授权支持，一般web应用不需要，测试用
                    .tokenStore(tokenStore) //jwt
//                    .approvalStoreDisabled() //不会出现授权多选框的页面，只有授权和禁用2项
//                    .pathMapping("/oauth/authorize", "/oauth/authorize") // Authorization endpoint
//                    .pathMapping("/oauth/token", "/oauth/token") //token endpoint
//                    .pathMapping("/oauth/confirm_access", "/oauth/confirm_access") //user posts approval for grants here
//                    .pathMapping("/oauth/error", "/oauth/error") //used to render errors in the authorization server
//                    .pathMapping("/oauth/check_token", "/oauth/check_token") //used by Resource Servers to decode access tokens)
//                    .pathMapping("/oauth/token_key", "/oauth/token_key") // exposes public key for token verification if using JWT tokens
                    .tokenEnhancer(accessTokenConverter())
            ;

        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//            security
//                    .tokenKeyAccess("permitAll()") //默认是denyAll(),需要开放给客户端获取token_key
//            ;
            security
                    .tokenKeyAccess("permitAll()")
//                    .tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
                    .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
        }
    }
}
