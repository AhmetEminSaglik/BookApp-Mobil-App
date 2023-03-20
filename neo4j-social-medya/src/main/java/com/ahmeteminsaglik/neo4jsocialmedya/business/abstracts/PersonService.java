package com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts;

import com.ahmeteminsaglik.neo4jsocialmedya.model.Person;

import java.util.List;

public interface PersonService {

    Person getPersonByName(String name);

    Iterable<Person> findPersonByNameLike(String name);

    List<Person> getPersonsWhoActAndDirect();

    List<Person> findAll();
}
