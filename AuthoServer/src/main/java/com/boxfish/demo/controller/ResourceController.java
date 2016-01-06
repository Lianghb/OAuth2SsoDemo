package com.boxfish.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by boxfish on 16/1/5.
 */
@RestController
public class ResourceController {
    @RequestMapping("/protected")
    public Object getMe() {
        return "access protected resource";
    }
}
