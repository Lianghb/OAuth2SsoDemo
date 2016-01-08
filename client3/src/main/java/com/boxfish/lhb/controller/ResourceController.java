/*
* Copyright (c) 2015 boxfish.cn. All Rights Reserved.
*/
package com.boxfish.lhb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

/**
 * Created with Intellij IDEA
 * Author: boxfish
 * Date: 16/1/8
 * Time: 17:09
 */

@RestController
public class ResourceController {

    @Value("${resource.client1.url}")
    String client1Url;

    @Value("${resource.client2.url}")
    String client2Url;

    @Value("${resource.client3.url}")
    String client3Url;

    @Value("${resource.protected.url}")
    String protectedUrl;

    @Autowired
    RestOperations restOperations;


    @RequestMapping("/client1")
    public String client1(HttpServletRequest request) throws Exception {
        String result = null;
        result = getFrom(client1Url, String.class);
        return "result : <p>" + result + "!</p>";
    }

    @RequestMapping("/client2")
    public String client2(HttpServletRequest request) throws Exception {
        String result = null;
        result = getFrom(client2Url, String.class);
        return "result : <p>" + result + "!</p>";
    }

    @RequestMapping("/client3")
    public String client3(HttpServletRequest request) throws Exception {
        String result = null;
        result = getFrom(client3Url, String.class);
        return "result : <p>" + result + "!</p>";
    }

    @RequestMapping("/protected")
    public String getProtectedResource(HttpServletRequest request) throws Exception {
        String result = null;
        result = getFrom(protectedUrl, String.class);
        return "result : <p>" + result + "!</p>";
    }


    public <T> T getFrom(String url, Class<T> t) {
        return restOperations.getForObject(URI.create(url), t);
    }


}
