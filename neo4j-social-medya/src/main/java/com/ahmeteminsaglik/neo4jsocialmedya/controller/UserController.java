package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.UserService;
import com.ahmeteminsaglik.neo4jsocialmedya.business.conretes.LoginUser;
import com.ahmeteminsaglik.neo4jsocialmedya.business.conretes.validation.ValidationLoginInput;
import com.ahmeteminsaglik.neo4jsocialmedya.business.conretes.validation.ValidationSignUp;
import com.ahmeteminsaglik.neo4jsocialmedya.model.User;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.exception.ApiRequestException;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.exception.response.InvalidUsernameOrPasswordException;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.DataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.Result;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.SuccessDataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.SuccessResult;
import org.apache.commons.lang3.ObjectUtils;
import org.neo4j.cypher.internal.expressions.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Body;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    private ValidationSignUp validationSignUp = new ValidationSignUp();
    private ValidationLoginInput validationLogin = new ValidationLoginInput();


    @GetMapping()
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/readbooks")
    public List<User> getAllWithReadBooksoca() {
        return userService.findAll();
    }

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
                System.out.println("Gelen user : "+dataResult.getData().toString());
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

    @DeleteMapping("/readbooks")
    public Result removeUserReadBookConnection(@RequestParam long userId, @RequestParam Long bookId) {
        userService.removeUserReadBookConnection(userId, bookId);
        return new SuccessResult("Connection is removed successfully");
    }

    @GetMapping("/followed/{userId}")
    public DataResult<List<User>> getFollowedUserList(@PathVariable long userId) {
        List<User> userList = userService.findAllFollowedUsersByUserId(userId);
        return new SuccessDataResult<>(userList, "User's followed users are retrived");
    }
    @GetMapping("/follower/{userId}")
    public DataResult<List<User>> getAllFollowersOfUserId(@PathVariable long userId) {
        List<User> userList = userService.findAllFollowersOfUserId(userId);
        return new SuccessDataResult<>(userList, "User's followed users are retrived");
    }
/*    @PostMapping("/post/followed")
    public DataResult<List<User>> getFollowedUserList2(@Body int userId) {
        List<User> userList = userService.findAllFollowedUsersByUserId(userId);
        return new SuccessDataResult<>(userList, "User's followed users are retrived");
    }*/
/*    @GetMapping("/read")
    public List<Read> getReadData() {
        return userService.findAllReadData();
    }*/
}
