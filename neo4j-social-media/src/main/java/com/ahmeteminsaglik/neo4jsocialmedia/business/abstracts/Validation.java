package com.ahmeteminsaglik.neo4jsocialmedia.business.abstracts;

import com.ahmeteminsaglik.neo4jsocialmedia.model.User;
import com.ahmeteminsaglik.neo4jsocialmedia.utility.result.DataResult;

public interface Validation {
    DataResult<User> validate(User user);
}
