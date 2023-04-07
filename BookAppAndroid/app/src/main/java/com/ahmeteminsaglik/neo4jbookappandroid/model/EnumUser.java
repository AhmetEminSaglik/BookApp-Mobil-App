package com.ahmeteminsaglik.neo4jbookappandroid.model;


public enum EnumUser {
    ID("id"),
    NAME("name"),
    LASTNAME("lastname"),
    USERNAME("username"),
    PASSWORD("password"),
    TOTALFOLLOWERS("totalFollowers");

    private final String name;

    EnumUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
