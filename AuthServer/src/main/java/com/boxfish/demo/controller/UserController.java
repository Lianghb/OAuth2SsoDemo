package com.boxfish.demo.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by boxfish on 16/1/5.
 */
@RestController
@RequestMapping("/oauth")
public class UserController {

    @RequestMapping("/user")
    public Object getUserInfo(Authentication authentication) throws Exception {
        return Collections.singletonMap("name",authentication.getName());
    }
}
