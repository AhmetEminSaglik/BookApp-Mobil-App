package com.ahmeteminsaglik.neo4jsocialmedia.utility.exception;

public class InvalidLoginInputException extends Exception {
    public InvalidLoginInputException() {
        super("Invalid username or password.");
    }
}
