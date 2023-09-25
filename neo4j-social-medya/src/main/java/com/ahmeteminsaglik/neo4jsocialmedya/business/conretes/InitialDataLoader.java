package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes;

import com.ahmeteminsaglik.neo4jsocialmedya.OpenLibraryFreeAPIData;
import com.ahmeteminsaglik.neo4jsocialmedya.business.StaticData;
import com.ahmeteminsaglik.neo4jsocialmedya.controller.AuthorController;
import com.ahmeteminsaglik.neo4jsocialmedya.controller.BookController;
import com.ahmeteminsaglik.neo4jsocialmedya.controller.UserController;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Author;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Book;
import com.ahmeteminsaglik.neo4jsocialmedya.model.User;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.CustomLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InitialDataLoader implements CommandLineRunner {
    private static CustomLog log = new CustomLog(InitialDataLoader.class);
    private Random random = new Random();
    @Autowired
    BookController bookController;
    @Autowired
    UserController userController;
    @Autowired
    AuthorController authorController;

    OpenLibraryFreeAPIData freeAPIData = new OpenLibraryFreeAPIData();

    @Override
    public void run(String... args) throws Exception {
        if (bookController.getAll().size() == 0) {
            saveInitilizateData();
        }
    }

    static int counter = 0;

    public void saveInitilizateData() {
        freeAPIData.createBookData(30);
//        Map<String, List<Book>> map = freeAPIData.getBookListMap();
        List<Author> authorList = freeAPIData.getAuthorList();
        System.out.println("Alinan Author List size : " + authorList.size());
        authorList = authorController.saveAll(authorList).getData();
        System.out.println("Saved Author List  size: " + authorList.size());
        processUserData();
        processBookData();
        setConnectionUserReadBook();
        fixAllRecordData();
    }

    private void fixAllRecordData() {
        userController.fixUserData();
        authorController.fixAuthorData();
        bookController.fixBookData();
    }

    private void processUserData() {
        if (userController.getAll().isEmpty()) {
            List<User> userList = userController.saveAll(StaticData.getUserList());
            User arr[][] = StaticData.getRelationArr();

            for (int i = 0; i < arr.length; i++) {
                userController.createNewConnectionFollowUser(arr[i][0].getId(), arr[i][1].getId());
            }

            userList.forEach(e -> {
                System.out.println("user : " + e);
            });


        }
    }

    private void setConnectionUserReadBook() {
        List<User> userList = userController.getAll();
        List<Book> bookList = bookController.getAll();
        System.out.println("User List Size : " + userList.size());
        System.out.println("Book List Size : " + bookList.size());
        for (int i = 0; i < userList.size(); i++) {
            int totalBookSize = getRandomTotalBookListSize(bookList.size() /3);
//            int totalBookSize = 1;//getRandomTotalBookListSize(bookList.size());
            List<Book> bookListOfUser = getRandomBooks(bookList, totalBookSize);
            System.out.println("bookListOfUser Size : " + bookListOfUser.size());
            for (Book tmp : bookListOfUser) {
                userController.setConnectionUserReadBook(userList.get(i).getId(), tmp.getId(), random.nextInt(10) + 1);
            }
        }
    }

    private int getRandomTotalBookListSize(int size) {
        return random.nextInt(size) + 3;
    }

    private List<Book> getRandomBooks(List<Book> bookList, int totalBookSize) {
        Set<Book> bookSet = new HashSet<>();
        while (bookSet.size() < totalBookSize) {
            System.out.println("getRandomBooks : totalBookSize : " + totalBookSize);
            int index = random.nextInt(totalBookSize);
            Book book = getBook(bookSet, bookList, index);
            System.out.println("eklenen Book id : "+book.getId());
            bookSet.add(book);

        }
        return new ArrayList<>(bookSet);
    }

    private Book getBook(Set<Book> bookSet, List<Book> bookList, int index) {
        System.out.println("getBook " + index);
        Book book = bookList.get(index % bookList.size());
        if (!bookSet.contains(book)) {
            return book;
        }
        return getBook(bookSet, bookList, ++index);
    }

    private void processBookData() {
        Map<String, List<Book>> map = freeAPIData.getBookListMap();

        for (Map.Entry<String, List<Book>> entry : map.entrySet()) {
            String autorKey = entry.getKey();
            Author author = authorController.findByKey(autorKey);
//            System.out.println("Author : " + author);
            List<Book> bookList = entry.getValue();
            bookController.saveAllBook(bookList);

            authorController.setWriteConnection(author, bookList);

        }

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
