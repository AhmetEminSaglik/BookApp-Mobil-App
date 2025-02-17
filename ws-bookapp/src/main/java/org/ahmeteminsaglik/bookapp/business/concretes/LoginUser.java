package org.ahmeteminsaglik.bookapp.business.concretes;

import org.ahmeteminsaglik.bookapp.business.abstracts.UserService;
import org.ahmeteminsaglik.bookapp.model.User;
import org.ahmeteminsaglik.bookapp.utility.result.DataResult;
import org.ahmeteminsaglik.bookapp.utility.result.ErrorDataResult;
import org.ahmeteminsaglik.bookapp.utility.result.SuccessDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class LoginUser {
    Logger logger = LoggerFactory.getLogger(LoginUser.class);
    private final UserService userService;

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
