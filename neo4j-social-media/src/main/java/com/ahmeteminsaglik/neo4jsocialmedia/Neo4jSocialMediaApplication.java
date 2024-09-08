package com.ahmeteminsaglik.neo4jsocialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Neo4jSocialMediaApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Neo4jSocialMediaApplication.class, args);
    }

}
