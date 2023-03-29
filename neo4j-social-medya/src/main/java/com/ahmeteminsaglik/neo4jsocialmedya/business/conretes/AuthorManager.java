package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.AuthorService;
import com.ahmeteminsaglik.neo4jsocialmedya.dataaccess.AuthorRepository;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorManager implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Author> findByHighestPoint() {
        return authorRepository.findByHighestPoint();
    }
}
