package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.PersonService;
import com.ahmeteminsaglik.neo4jsocialmedya.dataaccess.PersonRepository;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonManager implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person getPersonByName(String name) {
        return personRepository.getPersonByName(name);
    }

    @Override
    public Iterable<Person> findPersonByNameLike(String name) {
        return personRepository.findPersonByNameLike(name);
    }

    @Override
    public List<Person> getPersonsWhoActAndDirect() {
        return personRepository.getPersonsWhoActAndDirect();
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
