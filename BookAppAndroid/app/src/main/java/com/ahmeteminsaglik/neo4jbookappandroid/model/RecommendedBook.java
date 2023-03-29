package com.ahmeteminsaglik.neo4jbookappandroid.model;

public class RecommendedBook {
    private Book book;
    private String whyRecommend;

    public RecommendedBook(Book book, String whyRecommend) {
        this.book = book;
        this.whyRecommend = whyRecommend;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getWhyRecommend() {
        return whyRecommend;
    }

    public void setWhyRecommend(String whyRecommend) {
        this.whyRecommend = whyRecommend;
    }

    @Override
    public String toString() {
        return "RecommendedBook{" +
                "book=" + book +
                ", whyRecommend='" + whyRecommend + '\'' +
                '}';
    }
}
