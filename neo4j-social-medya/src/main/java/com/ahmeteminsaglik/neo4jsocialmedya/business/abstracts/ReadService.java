package com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts;

import com.ahmeteminsaglik.neo4jsocialmedya.model.Read;

import java.util.List;

public interface ReadService {
    List<Read> findAll();
}
