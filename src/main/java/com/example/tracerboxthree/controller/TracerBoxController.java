package com.example.tracerboxthree.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TracerBoxController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello from Tracer Box Three!";
    }

    @RequestMapping(value = "/last", method = RequestMethod.GET)
    public String returningLastAnswer(){
        System.out.println("last box method called");
        return "You have reached your final destination!";
    }
}
