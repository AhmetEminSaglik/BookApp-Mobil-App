package com.ahmeteminsaglik.neo4jsocialmedya.mapper;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.BookService;
import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.UserService;
import com.ahmeteminsaglik.neo4jsocialmedya.model.User;
import com.ahmeteminsaglik.neo4jsocialmedya.model.dto.UserFriendDTO;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.CustomLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    private UserService userService;
    private BookService bookService;
    private static CustomLog log = new CustomLog(UserMapper.class);


    @Autowired
    public UserMapper(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    public UserFriendDTO toUserFriendDTO(User user) {
        int readBookCount = bookService.getUserReadBookCount(user.getId());
        return new UserFriendDTO(user.getId(), user.getName(), user.getLastname(), user.getGender(), readBookCount, user.getImgUrl());
    }
}
