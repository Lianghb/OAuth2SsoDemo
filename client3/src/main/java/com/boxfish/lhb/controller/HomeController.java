package com.boxfish.lhb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by boxfish on 15/12/21.
 */
@Controller
public class HomeController {

    @Autowired
    RestOperations restOperations;

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Object home(Principal principal, HttpServletRequest request) {
        String redirect_uri = request.getRequestURI();
        System.out.println(String.format("redirect_uri=%s",redirect_uri));
        String username = principal.getName();
        String result = restOperations.getForObject("http://localhost:8090/server/protected"
                + "?" + "redirect_uri=" + redirect_uri, String.class);
        return String.format("Hello, %s\n<p>result from server : %s</p>", username, result);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object index() {
        return "index";
    }

}
