package com.ahmeteminsaglik.neo4jbookappandroid.model;

public class Book {
    private Long id;
    private  String name;
    private double point;
    private int totalRead;

    public Book(Long id, String name, double point, int totalRead) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.totalRead = totalRead;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public int getTotalRead() {
        return totalRead;
    }

    public void setTotalRead(int totalRead) {
        this.totalRead = totalRead;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", point=" + point +
                ", totalRead=" + totalRead +
                '}';
    }
}
