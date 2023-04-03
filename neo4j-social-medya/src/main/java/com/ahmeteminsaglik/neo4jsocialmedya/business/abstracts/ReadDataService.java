package com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts;

import com.ahmeteminsaglik.neo4jsocialmedya.model.ReadData;

import java.util.List;

public interface ReadDataService {
    List<ReadData> findAll();
}
