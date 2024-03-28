package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.BookService;
import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.UserService;
import com.ahmeteminsaglik.neo4jsocialmedya.business.conretes.LoginUser;
import com.ahmeteminsaglik.neo4jsocialmedya.business.conretes.validation.ValidationLoginInput;
import com.ahmeteminsaglik.neo4jsocialmedya.business.conretes.validation.ValidationSignUp;
import com.ahmeteminsaglik.neo4jsocialmedya.mapper.UserMapper;
import com.ahmeteminsaglik.neo4jsocialmedya.model.User;
import com.ahmeteminsaglik.neo4jsocialmedya.model.dto.UserFriendDTO;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.CustomLog;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.exception.ApiRequestException;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.exception.response.InvalidUsernameOrPasswordException;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.DataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.Result;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.SuccessDataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.SuccessResult;
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
    private static CustomLog log = new CustomLog(UserController.class);
    private final UserService userService;
    private ValidationSignUp validationSignUp = new ValidationSignUp();
    private ValidationLoginInput validationLogin = new ValidationLoginInput();
    private BookService bookService;

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

//    @GetMapping("/readbooks")
//    public List<User> getAllReadBookByUser() {
//        return userService.findAll();
//    }

    @GetMapping("/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    //    @ExceptionHandler
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
//        return new ErrorDataResult<>(result.getMessage());
        throw new ApiRequestException(result.getMessage(), new InvalidUsernameOrPasswordException());
    }

    public List<User> saveAll(List<User> userList) {
        return userService.saveAll(userList);
    }

    @DeleteMapping("/readbooks")
    public Result removeUserReadBookConnection(@RequestParam long userId, @RequestParam long bookId) {
        userService.removeUserReadBookConnection(userId, bookId);
        return new SuccessResult("Connection is removed successfully");
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
//        int userSize = userList.size();
        /*if (userSize < 5) {
            List<User> userListRandom = userService.findRandomUserToRecommend(userId);
            userList.addAll(userListRandom);
            Set<User> set = new HashSet<>(userList);
            userList = new ArrayList<>(set);
        }*/
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
//        List<User> userList = userService.findCommonUsersByFriends(userId);
//        int userSize = userList.size();
//        if (userSize < 5) {

        List<User> userList = userService.findCommonUsersByFriends(userId);
        List<User> randomUserList = userService.findRandomUserToRecommend(userId);

//        userList.forEach(e1 -> randomUserList.removeIf(e2 -> e1 == e2));
        userList.forEach(randomUserList::remove);

        Set<User> set = new HashSet<>(randomUserList);
        randomUserList = new ArrayList<>(set);
//        }
        List<UserFriendDTO> userDTOList = randomUserList
                .stream()
                .map(userMapper::toUserFriendDTO)
                .collect(Collectors.toList());

        DataResult dataResult = new SuccessDataResult<>(userDTOList, "Random userDTO list is succesfully retrived");
        log.info("Donecek deger : " + dataResult);
        return dataResult;
    }


    @GetMapping("/fix")
    public void fixUserData() {
        userService.fixUserData();
    }

    //@PostMapping("/read-book")
    @PostMapping("/read-book")
    public void setConnectionUserReadBook(@RequestParam long userId, @RequestParam long bookId, @RequestParam int rate) {
        userService.setConnectionUserReadBook(userId, bookId, rate);
    }

    @GetMapping("/count/book")
    public DataResult<Integer> getUserReadBookCount(@RequestParam long userId) {
        int count = bookService.getUserReadBookCount(userId);
        String msg = "User ID(" + userId + ") book count (" + count + ") is retrieved.";
        log.info("Msg : " + msg);
        return new SuccessDataResult<>(count, msg);
    }
    /*@GetMapping("/recommend/friend/{userId}")
    public DataResult<List<User>> getByMostReadBookFromFollowings(@PathVariable Long userId) {
        return new SuccessDataResult<>(userService.findCommonUsersByFriends(userId), "Data retrived Successfully");
    }*/
/*    @PostMapping("/post/following")
    public DataResult<List<User>> getfollowingUserList2(@Body int userId) {
        List<User> userList = userService.findAllfollowingUsersByUserId(userId);
        return new SuccessDataResult<>(userList, "User's following users are retrived");
    }*/
/*    @GetMapping("/read")
    public List<Read> getReadData() {
        return userService.findAllReadData();
    }*/
}
