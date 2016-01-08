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

    @RequestMapping("/client1")
    public Object getClient1Resources() {
        return "access client1 resources";
    }

    @RequestMapping("/client2")
    public Object getClient2Resources() {
        return "access client2 resources";
    }

    @RequestMapping("/client3")
    public Object getClient3Resources() {
        return "access client3 resources";
    }
}
