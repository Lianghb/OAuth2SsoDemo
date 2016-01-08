package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.Principal;

/**
 * Created by boxfish on 15/12/29.
 */


@SpringBootApplication
@EnableOAuth2Sso
@RestController
@PropertySource("classpath:my.properties")
public class Client1App {

    @Autowired
    Resources resources;

    @Bean
    public JwtAccessTokenConverter getJWtAccessTokenConverter(){
        return  new JwtAccessTokenConverter();
    }

    public static void main(String[] args) {
        SpringApplication.run(Client1App.class, args);
    }

    Logger logger = LoggerFactory.getLogger(Client1App.class);
    @Autowired
    RestTemplate restTemplate;


    @RequestMapping("/")
    public String home(HttpServletRequest request) throws Exception {
        String result = null;
        if (logger.isDebugEnabled()) {
            printCookies(request);
        }
        result = getFrom(resources.getResourceProtectedUrl(), String.class);
        return "result : <p>" + result + "!</p>";
    }

    @RequestMapping("/client1")
    public String client1(HttpServletRequest request) throws Exception {
        String result = null;
        result = getFrom(resources.getResourceClient1Url(), String.class);
        return "result : <p>" + result + "!</p>";
    }

    @RequestMapping("/client2")
    public String client2(HttpServletRequest request) throws Exception {
        String result = null;
        result = getFrom(resources.getResourceClient2Url(), String.class);
        return "result : <p>" + result + "!</p>";
    }

    @RequestMapping("/client3")
    public String client3(HttpServletRequest request) throws Exception {
        String result = null;
        result = getFrom(resources.getResourceClient3Url(), String.class);
        return "result : <p>" + result + "!</p>";
    }

    private void printCookies(HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        System.err.println(new ObjectMapper().writeValueAsString(cookies));
    }

    @RequestMapping("/hello")
    public String hello(Principal principal) {
        return "hello, " + principal.getName();
    }

    @RequestMapping("/detail")
    public Object details(Authentication authentication) {
        return authentication.getDetails();
    }


    @RequestMapping("/scopes")
    public Object scopes(Authentication authentication){
        return authentication.getAuthorities();
    }

    public <T> T getFrom(String url, Class<T> t) {
        return restTemplate.getForObject(URI.create(url), t);
    }



}