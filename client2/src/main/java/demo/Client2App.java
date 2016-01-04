package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;

/**
 * Created by boxfish on 16/1/4.
 */

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class Client2App {
    public static void main(String[] args) {
        SpringApplication.run(Client2App.class, args);
    }

    @Autowired
    RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/")
    public String home(HttpServletRequest request) throws Exception {
        String result = null;
        if (logger.isDebugEnabled()) {
            printCookies(request);
        }
        result = getFrom("http://localhost:8090/server/protected", String.class);
        return "result : <p>" + result + "!</p>";
    }

    private void printCookies(HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        System.err.println(new ObjectMapper().writeValueAsString(cookies));
    }

    @RequestMapping("/hello")
    public String hello(OAuth2Authentication authentication) {
        return "hello, " + authentication.getName();
    }

    @RequestMapping("/detail")
    public Object details(OAuth2Authentication authentication) {
        return authentication.getUserAuthentication();
    }


    public <T> T getFrom(String url, Class<T> t) {
        return restTemplate.getForObject(URI.create(url), t);
    }
}
