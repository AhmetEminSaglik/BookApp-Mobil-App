package com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts;

import com.ahmeteminsaglik.neo4jsocialmedya.model.User;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.DataResult;

public interface Validation {
    DataResult<User> validate(User user);
}
