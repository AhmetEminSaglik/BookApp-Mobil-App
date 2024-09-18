package org.ahmeteminsaglik.bookapp.business.abstracts;

import org.ahmeteminsaglik.bookapp.model.User;
import org.ahmeteminsaglik.bookapp.utility.result.DataResult;

public interface Validation {
    DataResult<User> validate(User user);
}
