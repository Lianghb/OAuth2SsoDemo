/*
* Copyright (c) 2015 boxfish.cn. All Rights Reserved.
*/
package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * Created with Intellij IDEA
 * Author: boxfish
 * Date: 16/1/8
 * Time: 15:11
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Client1App.class)
public class ResourcesTest {

    @Autowired
    Resources resources;

    @Test
    public void test(){
        Assert.hasText(resources.getResourceClient1Url(),String.format("url is valid \n url=%s",resources.getResourceClient1Url()));
    }
}
