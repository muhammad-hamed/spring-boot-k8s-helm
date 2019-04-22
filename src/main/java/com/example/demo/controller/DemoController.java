package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class DemoController {

    @GetMapping(value = "/hello")
    public String hello() throws UnknownHostException {
        return String.format("Hello from: %s!", InetAddress.getLocalHost().getHostAddress());
    }
}
