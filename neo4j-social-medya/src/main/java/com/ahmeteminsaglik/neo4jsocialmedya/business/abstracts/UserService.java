package com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts;

import com.ahmeteminsaglik.neo4jsocialmedya.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByName(String name);

    User findByUserNameAndPassword(String username, String password);

    User save(User user);

    User findByUsername(String username);

    List<User> findAllWithReadBooks();

    //    List<Read> findAllReadData();
    void removeUserReadBookConnection(long userId, long bookId);

    List<User> findAllFollowedUsersByUserId(long id);

    List<User> findAllFollowersOfUserId(long id);

    void removeUserFollowedRelationShipUser(long userId, long followedUserId);

    void removeUserFollowerRelationShipUser(long userId, long followedUserId);

    List<User> findCommonUsersByFriends(long userId);
    List<User> findRandomUserToRecommend(long userId);

    void createConnectionFollowFriend(long userId, long friendUserId);
}