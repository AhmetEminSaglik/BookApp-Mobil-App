package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.BookOLService;
import com.ahmeteminsaglik.neo4jsocialmedya.dataaccess.BookOLRepository;
import com.ahmeteminsaglik.neo4jsocialmedya.model.BookOL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookOLManager implements BookOLService {
    @Autowired
    private BookOLRepository repo;

    @Override
    public List<BookOL> findAll() {
        return repo.findAll();
    }

    @Override
    public BookOL save(BookOL book) {
        return repo.save(book);
    }

    @Override
    public List<BookOL> save(List<BookOL> list) {
        return repo.saveAll(list);
    }
}
