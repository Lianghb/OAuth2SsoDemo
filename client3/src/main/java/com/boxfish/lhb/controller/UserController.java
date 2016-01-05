package com.boxfish.lhb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * user目录下的页面控制
 * Created by boxfish on 15/12/14.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/simpleUserTable", method = RequestMethod.GET)
    public String simpleUser() {
        logger.debug("request /user/simpleUserTable");

        return "/user/simpleUserTable";
    }
}
