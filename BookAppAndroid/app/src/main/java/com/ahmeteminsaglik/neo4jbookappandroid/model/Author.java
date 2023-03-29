package com.ahmeteminsaglik.neo4jbookappandroid.model;

public class Author {
    private long id;
    private String name;
    private String lastname;
    private int totalBook;
    private double point;

    public Author(long id, String name, String lastname, int totalBook, double point) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.totalBook = totalBook;
        this.point = point;
    }

    public Author() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname= lastname;
    }

    public int getTotalBook() {
        return totalBook;
    }

    public void setTotalBook(int totalBook) {
        this.totalBook = totalBook;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalBook=" + totalBook +
                ", point=" + point +
                '}';
    }
}
