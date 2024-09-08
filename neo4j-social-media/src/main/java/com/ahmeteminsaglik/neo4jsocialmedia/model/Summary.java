package com.ahmeteminsaglik.neo4jsocialmedia.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Summary {

    private double average;
    private int count;

    @Override
    public String toString() {
        return "Summary{" +
                "average=" + average +
                ", count=" + count +
                '}';
    }
}
