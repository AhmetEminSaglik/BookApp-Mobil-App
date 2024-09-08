package com.ahmeteminsaglik.neo4jsocialmedia.utility.exception.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidUsernameOrPasswordException extends RuntimeException {
}
