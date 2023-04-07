package com.ahmeteminsaglik.neo4jbookappandroid.model;

public class RelationshipUser {
    private String name;
    private String lastname;
    private EnumRelationship enumRelationship;

    public RelationshipUser(String name, String lastname, EnumRelationship enumRelationship) {
        this.name = name;
        this.lastname = lastname;
        this.enumRelationship = enumRelationship;
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
        this.lastname = lastname;
    }

    public EnumRelationship getEnumRelationship() {
        return enumRelationship;
    }

    public void setEnumRelationship(EnumRelationship enumRelationship) {
        this.enumRelationship = enumRelationship;
    }
}
