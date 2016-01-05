package com.boxfish.lhb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by boxfish on 15/12/19.
 */

@Controller
public class LogController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Object logout(HttpServletRequest request) {
        logger.info("logout");
        request.setAttribute("logout", "logout");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Object login() {
        logger.info("login");
        return "login";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public Object error(HttpServletRequest request, Exception e) {
        logger.warn("error");
        logger.warn(e.getMessage());
        request.setAttribute("error", "error");
        return "login";
    }

}
