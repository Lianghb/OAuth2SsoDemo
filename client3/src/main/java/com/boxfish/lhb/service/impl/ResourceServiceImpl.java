/*
* Copyright (c) 2015 boxfish.cn. All Rights Reserved.
*/
package com.boxfish.lhb.service.impl;

import com.boxfish.lhb.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

/**
 * Created with Intellij IDEA
 * Author: boxfish
 * Date: 16/1/9
 * Time: 11:15
 */

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private RestOperations restOperations;

    @Value("${resource.client1.url}")
    private String resourceClient1Url;

    @Value("${resource.client2.url}")
    private String resourceClient2Url;

    @Value("${resource.client3.url}")
    private String resourceClient3Url;

    @Value("${resource.protected.url}")
    private String resourceProtectedUrl;

    @Override
    public String getResourceClient1() {
        return restOperations.getForObject(resourceClient1Url, String.class);
    }

    @Override
    public String getResourceClient2() {
        return restOperations.getForObject(resourceClient2Url, String.class);
    }

    @Override
    public String getResourceClient3() {
        return restOperations.getForObject(resourceClient3Url, String.class);
    }

    @Override
    public String getResourceProtected() {
        return restOperations.getForObject(resourceProtectedUrl, String.class);
    }
}
