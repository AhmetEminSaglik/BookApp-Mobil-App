package com.ahmeteminsaglik.neo4jsocialmedya.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorOL {
    private Long id;
    private String key;
    @Override
    public String toString() {
        return "AuthorOL{" +
                "key='" + key + '\'' +
                '}';
    }
}
