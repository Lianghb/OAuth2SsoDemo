package com.boxfish.lhb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by boxfish on 15/12/14.
 */
@SpringBootApplication
@PropertySource("classpath:my.properties")
//@EnableOAuth2Client
//@EnableOAuth2Sso
public class Client3App {
    public static void main(String[] args) {
        SpringApplication.run(Client3App.class, args);
    }
}