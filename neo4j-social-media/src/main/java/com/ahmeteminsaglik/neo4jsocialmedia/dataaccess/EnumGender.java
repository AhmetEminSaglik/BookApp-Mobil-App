package com.ahmeteminsaglik.neo4jsocialmedia.dataaccess;

public enum EnumGender {
    MAN(0, "man"), WOMAN(1, "woman");
    private final int id;
    private final String name;

    EnumGender(int id, String gender) {
        this.id = id;
        this.name = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
