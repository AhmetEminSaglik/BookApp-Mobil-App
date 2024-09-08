package com.ahmeteminsaglik.neo4jsocialmedia.mapper;

import com.ahmeteminsaglik.neo4jsocialmedia.business.abstracts.BookService;
import com.ahmeteminsaglik.neo4jsocialmedia.business.abstracts.UserService;
import com.ahmeteminsaglik.neo4jsocialmedia.model.User;
import com.ahmeteminsaglik.neo4jsocialmedia.model.dto.UserFriendDTO;
import com.ahmeteminsaglik.neo4jsocialmedia.utility.CustomLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    private static final CustomLog log = new CustomLog(UserMapper.class);
    private final UserService userService;
    private final BookService bookService;


    @Autowired
    public UserMapper(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    public UserFriendDTO toUserFriendDTO(User user) {
        int readBookCount = bookService.getUserReadBookCount(user.getId());
        int totalFollowers = userService.findAllFollowersOfUserId(user.getId()).size();
        int totalFollowing = userService.findAllfollowingUsersByUserId(user.getId()).size();
        UserFriendDTO userDTO = new UserFriendDTO(user.getId(), user.getName(), user.getLastname(), user.getGender(), readBookCount, totalFollowers, totalFollowing, user.getImgUrl());
        return userDTO;
    }
}
