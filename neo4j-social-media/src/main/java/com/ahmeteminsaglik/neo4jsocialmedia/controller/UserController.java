package com.ahmeteminsaglik.neo4jsocialmedia.controller;

import com.ahmeteminsaglik.neo4jsocialmedia.business.abstracts.BookService;
import com.ahmeteminsaglik.neo4jsocialmedia.business.abstracts.UserService;
import com.ahmeteminsaglik.neo4jsocialmedia.business.concretes.LoginUser;
import com.ahmeteminsaglik.neo4jsocialmedia.business.concretes.validation.ValidationLoginInput;
import com.ahmeteminsaglik.neo4jsocialmedia.business.concretes.validation.ValidationSignUp;
import com.ahmeteminsaglik.neo4jsocialmedia.mapper.UserMapper;
import com.ahmeteminsaglik.neo4jsocialmedia.model.User;
import com.ahmeteminsaglik.neo4jsocialmedia.model.dto.UserFriendDTO;
import com.ahmeteminsaglik.neo4jsocialmedia.utility.CustomLog;
import com.ahmeteminsaglik.neo4jsocialmedia.utility.exception.ApiRequestException;
import com.ahmeteminsaglik.neo4jsocialmedia.utility.exception.response.InvalidUsernameOrPasswordException;
import com.ahmeteminsaglik.neo4jsocialmedia.utility.result.DataResult;
import com.ahmeteminsaglik.neo4jsocialmedia.utility.result.Result;
import com.ahmeteminsaglik.neo4jsocialmedia.utility.result.SuccessDataResult;
import com.ahmeteminsaglik.neo4jsocialmedia.utility.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private static final CustomLog log = new CustomLog(UserController.class);
    private final UserService userService;
    private final ValidationSignUp validationSignUp = new ValidationSignUp();
    private final ValidationLoginInput validationLogin = new ValidationLoginInput();
    private final BookService bookService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping()
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    @PostMapping("/login")
    @ResponseBody
    public DataResult<User> login(@RequestBody User user) {
        DataResult<User> dataResult = validationLogin.validate(user);
        if (dataResult.isSuccess()) {
            dataResult = new LoginUser(userService).login(user);
            if (dataResult.isSuccess()) {
                System.out.println("Gelen user : " + dataResult.getData().toString());
                return dataResult;
            }
        }
        throw new ApiRequestException(dataResult.getMessage(), new InvalidUsernameOrPasswordException());
    }

    @PostMapping("/signup")
    @ResponseBody
    public DataResult<User> signup(@RequestBody User user) {
        validationSignUp.setUserService(userService);
        Result result = validationSignUp.validate(user);
        if (result.isSuccess()) {
            user = userService.save(user);
            return new SuccessDataResult(user, result.getMessage());
        }
        throw new ApiRequestException(result.getMessage(), new InvalidUsernameOrPasswordException());
    }

    public List<User> saveAll(List<User> userList) {
        return userService.saveAll(userList);
    }


    @GetMapping("/following/{userId}")
    public DataResult<List<UserFriendDTO>> getfollowingUserList(@PathVariable long userId) {
        List<User> userList = userService.findAllfollowingUsersByUserId(userId);
        List<UserFriendDTO> userFriendDTOList = userList
                .stream()
                .map(userMapper::toUserFriendDTO)
                .collect(Collectors.toList());
        return new SuccessDataResult<>(userFriendDTOList, "User's following users are retrived");
    }

    @GetMapping("/follower/{userId}")
    public DataResult<List<UserFriendDTO>> getAllFollowersOfUserId(@PathVariable long userId) {
        List<User> userList = userService.findAllFollowersOfUserId(userId);
        List<UserFriendDTO> userFriendDTOList = userList
                .stream()
                .map(userMapper::toUserFriendDTO)
                .collect(Collectors.toList());
        return new SuccessDataResult<>(userFriendDTOList, "User's following users are retrived");
    }

    @PostMapping("/{userId}/follow/{friendUserId}")
    public Result createNewConnectionFollowUser(@PathVariable long userId, @PathVariable long friendUserId) {
        userService.createConnectionFollowFriend(userId, friendUserId);
        return new SuccessResult("Connection is created");
    }

    @DeleteMapping("/{userId}/following/{followingUserId}")
    public Result removeUserfollowingRelationshipUser(@PathVariable long userId, @PathVariable long followingUserId) {
        userService.removeUserfollowingRelationshipUser(userId, followingUserId);
        return new SuccessResult("Relationship is deleted");
    }

    @DeleteMapping("/{userId}/follower/{followerUserId}")
    public Result removeUserFollowerRelationshipUser(@PathVariable long userId, @PathVariable long followerUserId) {
        userService.removeUserFollowerRelationshipUser(userId, followerUserId);
        log.info("Follower is removed :  " + followerUserId);
        userService.fixUserData();
        return new SuccessResult("Relationship is deleted");
    }

    @GetMapping("/recommend/user/{userId}")
    public DataResult<List<UserFriendDTO>> getRecommendedUserList(@PathVariable long userId) {
        List<User> userList = userService.findCommonUsersByFriends(userId);
        List<UserFriendDTO> userDTOList = userList
                .stream()
                .map(userMapper::toUserFriendDTO)
                .collect(Collectors.toList());
        DataResult dataResult = new SuccessDataResult<>(userDTOList, "Recommended userDTO list is succesfully retrived");
        log.info("Donecek deger : " + dataResult);
        return dataResult;
    }

    @GetMapping("/recommend/random/user/{userId}")
    public DataResult<List<UserFriendDTO>> getRandomRecommendedUserList(@PathVariable long userId) {
        List<User> userList = userService.findCommonUsersByFriends(userId);
        List<User> randomUserList = userService.findRandomUserToRecommend(userId);
        userList.forEach(randomUserList::remove);

        Set<User> set = new HashSet<>(randomUserList);
        randomUserList = new ArrayList<>(set);
        List<UserFriendDTO> userDTOList = randomUserList
                .stream()
                .map(userMapper::toUserFriendDTO)
                .collect(Collectors.toList());
        DataResult dataResult = new SuccessDataResult<>(userDTOList, "Random userDTO list is succesfully retrived");
        return dataResult;
    }

    @GetMapping("/fix")
    public void fixUserData() {
        userService.fixUserData();
    }

    @PostMapping("/read-book")
    public void setConnectionUserReadBook(@RequestParam long userId, @RequestParam long bookId, @RequestParam int rate) {
        bookService.createConnectionUserReadBook(userId, bookId, rate);
    }

    @GetMapping("/count/book")
    public DataResult<Integer> getUserReadBookCount(@RequestParam long userId) {
        int count = bookService.getUserReadBookCount(userId);
        String msg = "User ID(" + userId + ") book count (" + count + ") is retrieved.";
        log.info("Msg : " + msg);
        return new SuccessDataResult<>(count, msg);
    }
}
