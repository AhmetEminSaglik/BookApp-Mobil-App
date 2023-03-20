package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.PersonService;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
@CrossOrigin
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public Iterable<Person> findAllPersons() { return personService.findAll(); }
    @GetMapping("/name")
    public Iterable<Person> findPersonByName() { return personService.findPersonByNameLike("Ron Howard"); }
    @GetMapping("/size")
    public int getSize() {
        return personService.findAll().size();
    }
}
