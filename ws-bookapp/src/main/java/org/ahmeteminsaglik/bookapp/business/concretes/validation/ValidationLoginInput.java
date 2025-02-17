package org.ahmeteminsaglik.bookapp.business.concretes.validation;

import org.ahmeteminsaglik.bookapp.business.abstracts.Validation;
import org.ahmeteminsaglik.bookapp.model.User;
import org.ahmeteminsaglik.bookapp.utility.exception.InvalidInputException;
import org.ahmeteminsaglik.bookapp.utility.result.DataResult;
import org.ahmeteminsaglik.bookapp.utility.result.ErrorDataResult;
import org.ahmeteminsaglik.bookapp.utility.result.SuccessDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationLoginInput implements Validation {
    private final static int minLength = 1;
    private final static int maxLength = 15;
    Logger logger = LoggerFactory.getLogger(ValidationLoginInput.class);

    @Override
    public DataResult<User> validate(User user) {
        try {
            validateUsername(user.getUsername());
            validatePassword(user.getPassword());
            return new SuccessDataResult<>(user);
        } catch (InvalidInputException e) {
            logger.error(e.getMessage());
            return new ErrorDataResult(e.getMessage());
        }
    }

    private void validateUsername(String username) throws InvalidInputException {
        String inputArea = "Username";
        isDataFilled(inputArea, username);
        isTextLengthProper(inputArea, username, minLength, maxLength);
    }

    private void validatePassword(String password) throws InvalidInputException {
        String inputArea = "Password";
        isDataFilled(inputArea, password);
        isTextLengthProper(inputArea, password, minLength, maxLength);
    }

    private boolean isDataFilled(String inputArea, String text) throws InvalidInputException {
        if (text == null) {
            throw new InvalidInputException(inputArea);
        }
        return true;
    }

    private boolean isTextLengthProper(String inputArea, String text, int min, int max) throws InvalidInputException {
        if (text.length() < min || text.length() > max) {
            throw new InvalidInputException(inputArea, min, max);
        }
        return true;
    }
}
