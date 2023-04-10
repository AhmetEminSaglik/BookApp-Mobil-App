package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import org.ahmeteminsaglik.DataCreation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/")
public class DataCreationController {
    DataCreation dataCreation = new DataCreation();

    @GetMapping("create")
    public String getRandomNeo4jData() {
        return dataCreation.getQueryText().toString();
    }

    @GetMapping("fix")
    public String getFixDataQuery() {
        return dataCreation.getFixDataQueryText().toString();
    }

}
