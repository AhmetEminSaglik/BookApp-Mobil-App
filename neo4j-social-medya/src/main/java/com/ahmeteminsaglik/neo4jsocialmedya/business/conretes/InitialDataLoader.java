package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes;

import com.ahmeteminsaglik.neo4jsocialmedya.OpenLibraryFreeAPIData;
import com.ahmeteminsaglik.neo4jsocialmedya.controller.BookController;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Book;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.CustomLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class InitialDataLoader implements CommandLineRunner {
    private static CustomLog log = new CustomLog(InitialDataLoader.class);

    @Autowired
    BookController bookController;

    @Override
    public void run(String... args) throws Exception {
//        if (bookController.getAll().size() == 0) {
        saveInitilizateData();
//        }
    }

    static int counter = 0;

    public void saveInitilizateData() {
        OpenLibraryFreeAPIData freeAPIData = new OpenLibraryFreeAPIData();
        Map<String, List<Book>> map = freeAPIData.retrieveData(25);
        printAllAuthorKeyAndBooks(map);
/*        log.info("------------------------------ ALL");
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println((i + 1) + "-) book : " + bookList.get(i));
        }
        log.info("------------------------------ WITH DESCRIPTION");

        bookList.forEach(e -> {
            if (e.getDescription() != null) {
                counter++;
                System.out.println((counter) + "-) book : " + e);
            }

        });*/
//        for (int i = 0; i < bookList.size(); i++) {
//            System.out.println((i + 1) + "-) book : " + bookList.get(i));
//        }
//        bookController.saveAllBook(bookList);
    }

    private void printAllAuthorKeyAndBooks(Map<String, List<Book>> map) {
        System.out.println(">>>>>>>>>>>>>>>>> printAllAuthorKeyAndBooks ");
        for (Map.Entry<String, List<Book>> entry : map.entrySet()) {
            String authorKey = entry.getKey();
            List<Book> bookList = entry.getValue();

            System.out.println("authorKey: " + authorKey);

            for (int i = 0; i < bookList.size(); i++) {
                System.out.println((i + 1) + "-) book : " + bookList.get(i));
            }
        }
    }

}
