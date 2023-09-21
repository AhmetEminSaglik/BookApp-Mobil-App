package com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts;

import com.ahmeteminsaglik.neo4jsocialmedya.model.BookOL;

import java.util.List;

public interface BookOLService {
    List<BookOL> findAll();

    BookOL save(BookOL book);

    List<BookOL>  save(List<BookOL> list);
//    List<BookOL> save(List<BookOL> list);

}
