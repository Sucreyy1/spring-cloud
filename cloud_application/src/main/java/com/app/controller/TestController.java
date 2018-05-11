package com.app.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${request.key}")
    private String k;

    @Value("${request.value}")
    private String v;

    @RequestMapping("/hello")
    public String test(){
        return this.k+":"+this.v;
    }
}
