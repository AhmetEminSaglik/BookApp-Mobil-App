package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.ReadService;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Read;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/read")
@CrossOrigin
public class ReadController {
    @Autowired
    private ReadService service;

    @GetMapping()
    public List<Read> getAll() {
        return service.findAll();
    }
}
