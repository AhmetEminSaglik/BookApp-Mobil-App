package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.UserService;
import com.ahmeteminsaglik.neo4jsocialmedya.model.User;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.DataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.ErrorDataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.SuccessDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class LoginUser {
    Logger logger = LoggerFactory.getLogger(LoginUser.class);
    private UserService userService;

    public LoginUser(UserService userService) {
        this.userService = userService;
    }

    public DataResult<User> login(User user) {
        user = userService.findByUserNameAndPassword(user.getUsername(), user.getPassword());
        if (user == null) {
            return new ErrorDataResult<>(null, "Invalid username or password.");
        }
        return new SuccessDataResult<>(user, "Login successful");
    }

}
