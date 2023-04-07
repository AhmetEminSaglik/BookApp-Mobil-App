package com.ahmeteminsaglik.neo4jbookappandroid.model;

public enum EnumRelationship {
    FOLLOWED("followed"),
    FOLLOWER("follower");
    private final String name;

    EnumRelationship(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
