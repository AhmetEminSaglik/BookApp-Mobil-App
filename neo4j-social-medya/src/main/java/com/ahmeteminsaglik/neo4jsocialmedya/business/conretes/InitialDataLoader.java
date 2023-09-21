package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes;

import com.ahmeteminsaglik.neo4jsocialmedya.OpenLibraryFreeAPIData;
import com.ahmeteminsaglik.neo4jsocialmedya.controller.BookController;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Book;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.CustomLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitialDataLoader implements CommandLineRunner {
    private static CustomLog log = new CustomLog(InitialDataLoader.class);

    @Autowired
    BookController bookController;

    @Override
    public void run(String... args) throws Exception {
        if (bookController.getAll().size() == 0) {
            saveInitilizateData();
        }
    }

    public void saveInitilizateData() {
        OpenLibraryFreeAPIData freeAPIData = new OpenLibraryFreeAPIData();
        List<Book> bookList = freeAPIData.retrieveData(2000);
        bookController.saveAllBook(bookList);


    }


}
