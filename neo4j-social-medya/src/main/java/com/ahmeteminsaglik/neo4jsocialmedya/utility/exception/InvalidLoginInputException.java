package com.ahmeteminsaglik.neo4jsocialmedya.utility.exception;

public class InvalidLoginInputException extends Exception{
    public InvalidLoginInputException() {
        super("Invalid username or password.");
    }
}
