package com.ahmeteminsaglik.neo4jbookappandroid.model;

public class RecommendedUser {
    private  User user;
    private  String whyRecommend;

    public RecommendedUser(User user, String whyRecommend) {
        this.user = user;
        this.whyRecommend = whyRecommend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getWhyRecommend() {
        return whyRecommend;
    }

    public void setWhyRecommend(String whyRecommend) {
        this.whyRecommend = whyRecommend;
    }

    @Override
    public String toString() {
        return "RecommendedUser{" +
                "user=" + user +
                ", whyRecommend='" + whyRecommend + '\'' +
                '}';
    }
}
