package com.ahmeteminsaglik.neo4jsocialmedya.utility.result;

public class ErrorResult extends Result {
    public ErrorResult() {
        super(false);
    }

    public ErrorResult(String message) {
        super(false, message);
    }
}
