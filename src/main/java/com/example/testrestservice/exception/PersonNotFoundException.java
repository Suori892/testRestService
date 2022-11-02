package com.example.testrestservice.exception;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(Integer id) {
        super("Could not find person " + id);
    }
}
