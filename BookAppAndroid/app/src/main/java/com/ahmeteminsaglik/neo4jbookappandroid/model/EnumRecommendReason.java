package com.ahmeteminsaglik.neo4jbookappandroid.model;

public enum EnumRecommendReason {
    HIGH_POINT("HIGH POINT"),
    BEST_SELLER("BESTSELLER"),
    FRIEND("BY YOUR FRIENDS"),
    No_Recommend("");;


    private final String name;

    EnumRecommendReason(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
