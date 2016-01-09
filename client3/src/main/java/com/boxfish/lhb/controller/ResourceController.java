/*
* Copyright (c) 2015 boxfish.cn. All Rights Reserved.
*/
package com.boxfish.lhb.controller;

import com.boxfish.lhb.service.ResourceService;
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

    @Autowired
    ResourceService resourceService;

    @RequestMapping("/client1")
    public String client1(HttpServletRequest request) throws Exception {
        return "result : <p>" + resourceService.getResourceClient1() + "!</p>";
    }

    @RequestMapping("/client2")
    public String client2(HttpServletRequest request) throws Exception {
        return "result : <p>" + resourceService.getResourceClient2() + "!</p>";
    }

    @RequestMapping("/client3")
    public String client3(HttpServletRequest request) throws Exception {
        return "result : <p>" + resourceService.getResourceClient3() + "!</p>";

    }

    @RequestMapping("/protected")
    public String getProtectedResource(HttpServletRequest request) throws Exception {
        return "result : <p>" + resourceService.getResourceProtected() + "!</p>";

    }
}
