package com.ahmeteminsaglik.neo4jsocialmedia.controller;

import com.ahmeteminsaglik.neo4jsocialmedia.business.concretes.DataCreation;
import com.ahmeteminsaglik.neo4jsocialmedia.utility.result.Result;
import com.ahmeteminsaglik.neo4jsocialmedia.utility.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data-creation")
@CrossOrigin
public class DataCreationController {

    private DataCreation dataCreation;

    @Autowired
    public DataCreationController(DataCreation dataCreation) {
        this.dataCreation = dataCreation;
    }

    @GetMapping
    public ResponseEntity<Result> createDataRequest() {
        boolean result = dataCreation.isDataCreated();
        if (result) {
            return ResponseEntity.ok().body(new SuccessResult("Data is already created."));
        } else {
            return ResponseEntity.ok().body(new SuccessResult("Data creation is processing."));
        }
    }

}
