package com.boxfish.lhb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.thymeleaf.context.WebContext;

/**
 * Created by boxfish on 15/12/14.
 */
@SpringBootApplication
@PropertySource("classpath:my.properties")
public class Client3App {
    public static void main(String[] args) {
        SpringApplication.run(Client3App.class, args);
    }
}