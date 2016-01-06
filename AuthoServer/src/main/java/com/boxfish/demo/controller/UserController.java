package com.boxfish.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Map result = new HashMap<>(1);
        result.put("name", authentication.getName());
        return result;
    }
}
