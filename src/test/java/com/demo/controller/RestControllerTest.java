package com.demo.controller;

import com.demo.model.PostBody;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RestControllerTest {

    RestController controller = new RestController();

    private PostBody routeOk(){
        PostBody postBody = new PostBody();
        postBody.setDestination("WWW");
        postBody.setSource("QQQ");
        postBody.setPrice(30);
        return postBody;
    }

    @Test
    public void addRouteOk(){
        PostBody postBody = routeOk();
        controller.addRoute(postBody);

    }
}
