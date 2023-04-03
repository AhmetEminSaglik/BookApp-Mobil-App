package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.ReadDataService;
import com.ahmeteminsaglik.neo4jsocialmedya.model.ReadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/read")
@CrossOrigin
public class ReadDataController {
    @Autowired
    private ReadDataService service;

    @GetMapping()
    public List<ReadData> getAll() {
        return service.findAll();
    }

}
