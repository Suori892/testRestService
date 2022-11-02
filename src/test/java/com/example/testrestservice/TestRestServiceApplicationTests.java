package com.example.testrestservice;

import com.example.testrestservice.model.Person;
import com.example.testrestservice.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootTest
class TestRestServiceApplicationTests {

    PersonRepository repo;

    @Autowired
    public TestRestServiceApplicationTests(PersonRepository repo) {
        this.repo = repo;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testSinglePerson() {
        Person person = repo.findById(1).get();
        Assertions.assertEquals("Bob", person.getFirstName());
    }

    @Test
    public void ensureThatUserAPICallReturnStatusCode400() throws Exception {

    }
}
