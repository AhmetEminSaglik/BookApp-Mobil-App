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
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> findAllWithReadBooks() {
        return userRepository.findAllWithBooks();
    }

    @Override
    public void removeUserReadBookConnection(long userId, long bookId) {
        userRepository.removeUserReadBookConnection(userId, bookId);
    }

    @Override
    public List<User> findAllFollowedUsersByUserId(long id) {
        return userRepository.findAllFollowedUsersByUserId(id);
    }

    @Override
    public List<User> findAllFollowersOfUserId(long id) {
        return userRepository.findAllFollowersOfUserId(id);
    }

    @Override
    public void removeUserFollowedRelationShipUser(long userId, long followedUserId) {
        userRepository.removeUserFollowedRelationShipUser(userId, followedUserId);
    }

    @Override
    public void removeUserFollowerRelationShipUser(long userId, long followerUserId) {
        userRepository.removeUserFollowerRelationShipUser(userId, followerUserId);
    }

    @Override
    public List<User> findCommonUsersByFriends(long userId) {
        return userRepository.findCommonUsersByFriends(userId);
    }

    @Override
    public void createConnectionFollowFriend(long userId, long friendUserId) {
        userRepository.createConnectionFollowFriend(userId, friendUserId);
    }

    @Override
    public User findByUserNameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


}
