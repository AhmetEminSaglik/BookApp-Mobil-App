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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

/*    @GetMapping("/read")
    public List<Read> getReadData() {
        return userService.findAllReadData();
    }*/
}
