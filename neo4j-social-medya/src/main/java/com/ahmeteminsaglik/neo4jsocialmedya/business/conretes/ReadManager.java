package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.ReadService;
import com.ahmeteminsaglik.neo4jsocialmedya.dataaccess.ReadRepository;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Read;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadManager implements ReadService {
    @Autowired
    ReadRepository repository;

    @Override
    public List<Read> findAll() {
        return repository.findAll();
    }
}
