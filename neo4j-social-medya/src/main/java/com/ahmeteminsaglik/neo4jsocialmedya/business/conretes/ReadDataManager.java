package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.ReadDataService;
import com.ahmeteminsaglik.neo4jsocialmedya.dataaccess.ReadDataRepository;
import com.ahmeteminsaglik.neo4jsocialmedya.model.ReadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadDataManager implements ReadDataService {
    @Autowired
    ReadDataRepository repository;

    @Override
    public List<ReadData> findAll() {
        return repository.findAll();
    }
}
