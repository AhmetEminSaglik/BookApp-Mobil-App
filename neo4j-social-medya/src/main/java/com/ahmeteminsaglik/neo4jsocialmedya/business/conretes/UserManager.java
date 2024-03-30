package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.UserService;
import com.ahmeteminsaglik.neo4jsocialmedya.dataaccess.UserRepository;
import com.ahmeteminsaglik.neo4jsocialmedya.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User findById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public User findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public User findByUsername(String name) {
        return repo.findByUsername(name);
    }

    @Override
    public List<User> findAllWithReadBooks() {
        return repo.findAllWithBooks();
    }

    @Override
    public void removeUserReadBookConnection(long userId, long bookId) {
        repo.removeUserReadBookConnection(userId, bookId);
    }

    @Override
    public List<User> findAllfollowingUsersByUserId(long id) {
        return repo.findAllfollowingUsersByUserId(id);
    }

    @Override
    public List<User> findAllFollowersOfUserId(long id) {
        return repo.findAllFollowersOfUserId(id);
    }

    @Override
    public void removeUserfollowingRelationshipUser(long userId, long followingUserId) {
        repo.removeUserfollowingRelationshipUser(userId, followingUserId);
    }

    @Override
    public void removeUserFollowerRelationshipUser(long userId, long followerUserId) {
        repo.removeUserFollowerRelationshipUser(userId, followerUserId);
    }

    @Override
    public List<User> findCommonUsersByFriends(long userId) {
        return repo.findCommonUsersByFriends(userId);
    }

    @Override
    public List<User> findRandomUserToRecommend(long userId) {
        return repo.findRandomUserToRecommend(userId);
    }


    @Override
    public void createConnectionFollowFriend(long userId, long friendUserId) {
        repo.createConnectionFollowFriend(userId, friendUserId);
    }

    @Override
    public void fixUserData() {
        repo.fixUserData();
    }

    @Override
    public void setConnectionUserReadBook(long userId, long bookId, int rate) {
        repo.setConnectionUserReadBook(userId, bookId, rate);
    }

    @Override
    public User findByUserNameAndPassword(String username, String password) {
        return repo.findByUsernameAndPassword(username, password);
    }

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public List<User> saveAll(List<User> userList) {
        return repo.saveAll(userList);
    }


}
