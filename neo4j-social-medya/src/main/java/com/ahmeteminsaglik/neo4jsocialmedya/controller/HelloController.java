package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class HelloController {
    @GetMapping
    public String hello(){
        return  "<h1>hello. Server is running</h1>";
    }
}
