package com.example.testrestservice.service;

import com.example.testrestservice.model.Person;
import com.example.testrestservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService implements IPersonService{

    PersonRepository repo;

    @Autowired
    public PersonService(PersonRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return repo.findById(id);
    }
}
