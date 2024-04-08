package com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts;

import com.ahmeteminsaglik.neo4jsocialmedya.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User findByName(String name);

    User findByUserNameAndPassword(String username, String password);

    User save(User user);

    User findByUsername(String username);

    List<User> findAllWithReadBooks();

    //    List<Read> findAllReadData();

    List<User> findAllfollowingUsersByUserId(long id);

    List<User> findAllFollowersOfUserId(long id);

    void removeUserfollowingRelationshipUser(long userId, long followingUserId);

    void removeUserFollowerRelationshipUser(long userId, long followingUserId);

    List<User> findCommonUsersByFriends(long userId);

    List<User> findRandomUserToRecommend(long userId);

    void createConnectionFollowFriend(long userId, long friendUserId);

    List<User> saveAll(List<User> userList);

    void fixUserData();

//    void setConnectionUserReadBook(long userId, long bookId, int rate);
}