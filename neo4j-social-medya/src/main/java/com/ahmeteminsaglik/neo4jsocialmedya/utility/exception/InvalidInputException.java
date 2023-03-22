package com.ahmeteminsaglik.neo4jsocialmedya.utility.exception;

public class InvalidInputException extends Exception {
    final static String errMsgPrefix = "--> INVALID INPUT EXCEPTION :";

    public InvalidInputException(String text, int min, int max) {
        super(errMsgPrefix + "Input is :" + text + ". Text size must be between " + min + " and " + max + " to sign up.");
    }

    public InvalidInputException(String inputArea, String value) {
        super(errMsgPrefix + "-->  " + inputArea + " is " + value + ". Please fill input.");
    }
}
