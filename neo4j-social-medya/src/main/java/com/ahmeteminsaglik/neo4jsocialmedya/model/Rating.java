package com.ahmeteminsaglik.neo4jsocialmedya.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating {

    private Summary summary;

    @Override
    public String toString() {
        return "Rating{" +
                "summary=" + summary +
                '}';
    }
}
