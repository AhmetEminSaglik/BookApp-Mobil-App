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
}
