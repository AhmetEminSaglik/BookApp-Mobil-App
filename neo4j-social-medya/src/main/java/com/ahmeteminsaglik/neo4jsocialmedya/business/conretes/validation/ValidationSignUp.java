package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes.validation;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.Validation;
import com.ahmeteminsaglik.neo4jsocialmedya.model.User;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.exception.InvalidInputException;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationSignUp implements Validation {
    Logger logger = LoggerFactory.getLogger(ValidationSignUp.class);
    private final static int minLength = 3;
    private final static int maxLength = 10;

    @Override
    public DataResult<User> validate(User user) {
        try {
            validateName(user.getName());
            validateLastname(user.getLastname());
            validateUsername(user.getUsername());
            validatePassword(user.getPassword());
            return new SuccessDataResult<>(user, "User is sing up successfully");
        } catch (InvalidInputException e) {
            logger.error(e.getMessage());
            return new ErrorDataResult(e.getMessage());
        }
    }

    private void validateName(String name) throws InvalidInputException {
        isDataFilled("name", name);
        isTextLengthProper(name, minLength, maxLength);
    }

    private void validateLastname(String lastname) throws InvalidInputException {
        isDataFilled("lastname", lastname);
        isTextLengthProper(lastname, minLength, maxLength);
    }

    private void validateUsername(String username) throws InvalidInputException {
        isDataFilled("username", username);
        isTextLengthProper(username, minLength, maxLength);
    }

    private void validatePassword(String password) throws InvalidInputException {
        isDataFilled("password", password);
        isTextLengthProper(password, minLength, maxLength);
    }


    private boolean isTextLengthProper(String text, int min, int max) throws InvalidInputException {
        if (text.length() < min || text.length() > max) {
            throw new InvalidInputException(text, min, max);
        }
        return true;
    }

    private boolean isDataFilled(String inputArea, String text) throws InvalidInputException {
        if (text == null) {
            throw new InvalidInputException(inputArea, text);
        }
        return true;
    }

}
