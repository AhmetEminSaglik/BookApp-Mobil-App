package com.ahmeteminsaglik.neo4jbookappandroid.model;

public class RecommendedAuthor {
    private Author author;
    private String whyRecommend;

    public RecommendedAuthor(Author author, String whyRecommend) {
        this.author = author;
        this.whyRecommend = whyRecommend;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getWhyRecommend() {
        return whyRecommend;
    }

    public void setWhyRecommend(String whyRecommend) {
        this.whyRecommend = whyRecommend;
    }

    @Override
    public String toString() {
        return "RecommendedAuthor{" +
                "author=" + author +
                ", whyRecommend='" + whyRecommend + '\'' +
                '}';
    }
}
