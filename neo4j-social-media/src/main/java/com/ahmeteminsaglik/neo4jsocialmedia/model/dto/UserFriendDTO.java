package com.ahmeteminsaglik.neo4jsocialmedia.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserFriendDTO {
    private long id;
    private String name;
    private String lastname;
    private String gender;
    private int totalReadBook;
    private int totalFollowers;
    private int totalFollowing;

    private String imgUrl;
}
