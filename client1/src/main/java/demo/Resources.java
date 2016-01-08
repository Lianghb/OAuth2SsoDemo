/*
* Copyright (c) 2015 boxfish.cn. All Rights Reserved.
*/
package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Created with Intellij IDEA
 * Author: boxfish
 * Date: 16/1/8
 * Time: 14:41
 */

@Repository
public class Resources {

    @Value("${resource.client1.url}")
    private  String RESOURCE_CLIENT1_URL;

    @Value("${resource.client2.url}")
    private  String RESOURCE_CLIENT2_URL;

    @Value("${resource.client3.url}")
    private  String RESOURCE_CLIENT3_URL;

    @Value("${resource.protected.url}")
    private  String RESOURCE_PROTECTED_URL;

    public  String getResourceClient1Url() {
        Assert.hasText(RESOURCE_CLIENT1_URL, "invalid state (RESOURCE_CLIENT1_URL is empty)");
        return RESOURCE_CLIENT1_URL;
    }

    public  String getResourceClient2Url() {
        return RESOURCE_CLIENT2_URL;
    }

    public  String getResourceClient3Url() {
        return RESOURCE_CLIENT3_URL;
    }

    public  String getResourceProtectedUrl() {
        Assert.hasText(RESOURCE_PROTECTED_URL, "invalid state (RESOURCE_PROTECTED_URL is empty)");
        return RESOURCE_PROTECTED_URL;
    }


    public  void setResourceClient1Url(String resourceClient1Url) {
        RESOURCE_CLIENT1_URL = resourceClient1Url;
    }

    @Value("${resource.client2.url}")
    public  void setResourceClient2Url(String resourceClient2Url) {
        RESOURCE_CLIENT2_URL = resourceClient2Url;
    }

    @Value("${resource.client3.url}")
    public  void setResourceClient3Url(String resourceClient3Url) {
        RESOURCE_CLIENT3_URL = resourceClient3Url;
    }

    @Value("${resource.protected.url}")
    public  void setResourceProtectedUrl(String resourceProtectedUrl) {
        RESOURCE_PROTECTED_URL = resourceProtectedUrl;
    }
}
