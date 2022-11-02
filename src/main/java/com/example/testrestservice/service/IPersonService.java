package com.example.testrestservice.service;

import com.example.testrestservice.model.Person;

import java.util.Optional;

public interface IPersonService {
    Optional<Person> findById(Integer id);
}
