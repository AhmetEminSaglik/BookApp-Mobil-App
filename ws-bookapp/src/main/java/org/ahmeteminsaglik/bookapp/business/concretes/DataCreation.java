package org.ahmeteminsaglik.bookapp.business.concretes;

import org.ahmeteminsaglik.bookapp.OpenLibraryFreeAPIData;
import org.ahmeteminsaglik.bookapp.business.StaticData;
import org.ahmeteminsaglik.bookapp.controller.AuthorController;
import org.ahmeteminsaglik.bookapp.controller.BookController;
import org.ahmeteminsaglik.bookapp.controller.UserController;
import org.ahmeteminsaglik.bookapp.model.Author;
import org.ahmeteminsaglik.bookapp.model.Book;
import org.ahmeteminsaglik.bookapp.model.User;
import org.ahmeteminsaglik.bookapp.utility.CustomLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

//@Component
//public class InitialDataLoader implements CommandLineRunner {
@Component
public class DataCreation {
    private static final CustomLog log = new CustomLog(DataCreation.class);
    @Autowired
    BookController bookController;
    @Autowired
    UserController userController;
    @Autowired
    AuthorController authorController;
    OpenLibraryFreeAPIData freeAPIData = new OpenLibraryFreeAPIData();
    private boolean dataIsCreated = false;
    private final Random random = new Random();

    public synchronized boolean isDataCreated() {
        if (dataIsCreated == false && bookController.getAll().getBody().getData().size() == 0) {
            dataIsCreated = true;
            isDataCreated();
            new Thread(() -> createData()).start();
            return false;
        } else {
            return true;
        }
    }
/*
    @Override
    public void run(String... args) throws Exception {
        if (bookController.getAll().getBody().getData().size() == 0) {
            saveInitilizateData();
        }
    }
*/

    public void createData() {
        //30 books are retrieved from OpenLibrary
        if (isDataCreated()) {
            freeAPIData.createBookData();
            List<Author> authorList = freeAPIData.getAuthorList();
            authorList = authorController.saveAll(authorList).getData();
            System.out.println("saved authorList data: ");
            for (int i = 0; i < authorList.size(); i++) {
                System.out.println((i + 1) + "-) Author data : " + authorList.get(i));
            }
            processUserData();
            processBookData();
            setConnectionUserReadBook();
            fixAllRecordData();
        }
    }

    private void fixAllRecordData() {
        userController.fixUserData();
        authorController.fixAuthorData();
        bookController.fixBookData();
    }

    private List<Book> fixUnknowCharsBook(List<Book> bookList) {
        for (int i = 0; i < bookList.size(); i++) {
            bookList.get(i).setName(clearUnknowChars(bookList.get(i).getName()));
            bookList.get(i).setDescription(clearUnknowChars(bookList.get(i).getDescription()));
        }
        return bookList;
    }

    private String clearUnknowChars(String data) {
        String cleanedString = data.replaceAll("[^a-zA-Z0-9\\s]", "");
        return cleanedString;
    }

    private void processUserData() {
        if (userController.getAll().isEmpty()) {
            List<User> userList = userController.saveAll(StaticData.getUserList());
            User[][] arr = StaticData.getRelationArr();
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
        List<Book> bookList = bookController.getAll().getBody().getData();
        for (int i = 0; i < userList.size(); i++) {
            int totalBookSize = getRandomTotalBookListSize(bookList.size() / 3);
            List<Book> bookListOfUser = getRandomBooks(bookList, totalBookSize);
            for (Book tmp : bookListOfUser) {
                userController.setConnectionUserReadBook(userList.get(i).getId(), tmp.getId(), random.nextInt(5) + 1);
            }
        }
    }

    private int getRandomTotalBookListSize(int size) {
        return random.nextInt(size) + 3;
    }

    private List<Book> getRandomBooks(List<Book> bookList, int totalBookSize) {
        Set<Book> bookSet = new HashSet<>();
        while (bookSet.size() < totalBookSize) {
            int index = random.nextInt(totalBookSize);
            Book book = getBook(bookSet, bookList, index);
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
            List<Book> bookList = entry.getValue();
            bookList.forEach(e -> {
                System.out.println(e.getPoint());
            });
            bookList = fixUnknowCharsBook(bookList);
            bookController.saveAllBook(bookList);
            authorController.setWriteConnection(author, bookList);
        }
    }

    private void printAllAuthorKeyAndBooks(Map<String, List<Book>> map) {
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
