package com.ahmeteminsaglik.bookdemoapi.business;

import com.ahmeteminsaglik.bookdemoapi.model.Author;
import com.ahmeteminsaglik.bookdemoapi.model.Book;

import java.util.ArrayList;
import java.util.List;

public class ExampleData {

    private static List<Author> authorList = new ArrayList<>();
    private static List<Book> bookList = new ArrayList<>();

    public ExampleData() {
        if (authorList.size() == 0) {
            createAuthorList();
        }
        if (bookList.size() == 0) {
            createBookList();
        }
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    private void createBookList() {
        bookList.add(new Book("Effective Java", 416, authorList.get(0)));
        bookList.add(new Book("Clean Code", 464, authorList.get(1)));
        bookList.add(new Book("Atomic Habits", 306, authorList.get(2)));
    }

    private void createAuthorList() {
        authorList.add(new Author("Joshua", "Bloch"));
        authorList.add(new Author(" Robert Cecil ", "Martin"));
        authorList.add(new Author(" James ", "Clear"));
    }

}
