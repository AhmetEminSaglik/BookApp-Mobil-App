package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.UserService;
import com.ahmeteminsaglik.neo4jsocialmedya.business.conretes.ValidationSignUp;
import com.ahmeteminsaglik.neo4jsocialmedya.model.User;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.LoginRequestData;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.DataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.ErrorDataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.Result;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private ValidationSignUp validationSignUp = new ValidationSignUp();
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestBody LoginRequestData loginRequestData) {
        return userService.findByUserNameAndPassword(loginRequestData.getUsername(), loginRequestData.getPassword());
    }

    @PostMapping("/signup")
    public DataResult<User> signup(@RequestBody User user) {
        Result result = validationSignUp.validate(user);
        if (result.isSuccess()) {
            user = userService.save(user);
            return new SuccessDataResult(user, result.getMessage());
        }

        return new ErrorDataResult<>(result.getMessage());

    }
}
