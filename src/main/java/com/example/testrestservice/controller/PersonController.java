package com.example.testrestservice.controller;

import com.example.testrestservice.exception.PersonNotFoundException;
import com.example.testrestservice.model.Person;
import com.example.testrestservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController {

    PersonRepository repo;

    @Autowired
    public PersonController(PersonRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{id}")
    public Person show(@PathVariable("id") int id) {
        return repo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}
