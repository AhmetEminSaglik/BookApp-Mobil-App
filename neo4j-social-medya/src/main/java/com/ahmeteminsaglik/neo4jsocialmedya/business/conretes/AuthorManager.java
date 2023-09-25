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
    AuthorRepository repo;

    @Override
    public List<Author> findByHighestPoint() {
        return repo.findByHighestPoint();
    }

    @Override
    public void fixAuthorData() {
        repo.fixAuthorData();
    }

    @Override
    public List<Author> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Author> saveAll(List<Author> authorList) {
        return repo.saveAll(authorList);
    }

    @Override
    public void setWriteConnection(long authorId, long bookId) {
        repo.setWriteConnection(authorId, bookId);
    }

    @Override
    public Author findByKey(String authorKey) {
        return repo.findByKey(authorKey);
    }
}
