package com.example.testrestservice;

import com.example.testrestservice.controller.PersonController;
import com.example.testrestservice.model.Person;
import com.example.testrestservice.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestRestServiceApplicationTests {

    private PersonRepository repo;
    private PersonController controller;
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    @Autowired
    public TestRestServiceApplicationTests(PersonRepository repo, PersonController controller, TestRestTemplate restTemplate) {
        this.repo = repo;
        this.controller = controller;
        this.restTemplate = restTemplate;
    }

    @Test
    void contextLoads() {
    }
    //Rest controller tests
    @Test
    @Order(1)
    @DisplayName("Check persons id db")
    public void testEveryPerson() {
       Person person1 = new Person("Bob", "Martin", 70);
       Person person2 = new Person("James", "Gosling", 67);
       Person person3 = new Person("Guido van", "Rossum", 66);
       assertEquals(person1, repo.findById(1).get());
       assertEquals(person2, repo.findById(2).get());
       assertEquals(person3, repo.findById(3).get());
    }

    @Test
    @Order(2)
    @DisplayName("The class marked as RestController")
    public void classMarkedAsRestController() {
        assertTrue(PersonController.class.getAnnotation(RestController.class) != null);
    }


    @Test
    @Order(4)
    @DisplayName("Controller is not null")
    public void testController() {
        assertNotNull(controller);
    }
    @Test
    @Order(5)
    @DisplayName("Controller return Bob")
    public void testLink1() throws Exception{
        assertTrue(this.restTemplate
                .getForObject("http://localhost:" + port
                        + "/person/1", String.class).contains("Bob"));
    }

    @Test
    @Order(6)
    @DisplayName("Controller return James")
    public void testLink2() throws Exception{
        assertTrue(this.restTemplate
                .getForObject("http://localhost:" + port
                        + "/person/2", String.class).contains("James"));
    }

    @Test
    @Order(7)
    @DisplayName("Controller return Guido van")
    public void testLink3() throws Exception{
        assertTrue(this.restTemplate
                .getForObject("http://localhost:" + port
                        + "/person/3", String.class).contains("Guido van"));
    }
    //Person tests
    @Test
    @Order(8)
    @DisplayName("The class is marked as JPA entity")
    public void classMarkedAsJpaEntity() {
        assertTrue(Person.class.getAnnotation(Entity.class) != null);
    }

    @Test
    @Order(9)
    @DisplayName("The entity has an ID")
    public void entityHasId() throws NoSuchFieldException {
        Field idField = Person.class.getDeclaredField("id");

        assertTrue(idField.getAnnotation(Id.class) != null);
    }

    @Test
    @Order(10)
    @DisplayName("@Table name is specified")
    public void tableIsSpecified() {
        Table table = Person.class.getAnnotation(Table.class);

        assertTrue(table != null);
        assertTrue(table.name().equals("people"));
    }

    @Test
    @Order(11)
    @DisplayName("Id field type is Integer")
    public void idTypeIsInteger() throws NoSuchFieldException {
        Field idField = Person.class.getDeclaredField("id");
        String idTypeName = idField.getType().getName();
        String intName = Integer.class.getName();

        assertEquals(idTypeName, intName);
    }

    @Test
    @Order(12)
    @DisplayName("Id field is marked as generated value")
    public void idIsGenerated() throws NoSuchFieldException {
        Field idField = Person.class.getDeclaredField("id");

        assertTrue(idField.getAnnotation(GeneratedValue.class) != null);
    }

    @Test
    @Order(13)
    @DisplayName("Id generation strategy is Identity")
    public void idGenerationStrategyIsIdentity() throws NoSuchFieldException {
        Field idField = Person.class.getDeclaredField("id");
        GeneratedValue generatedValue = idField.getAnnotation(GeneratedValue.class);

        assertTrue(generatedValue.strategy().equals(GenerationType.IDENTITY));
    }

    //Test Repository
    @Test
    @Order(14)
    @DisplayName("Repository have annotation @Repository")
    public void repositorySpecified() {
        assertTrue(PersonRepository.class.getAnnotation(Repository.class) != null);
    }

    @Test
    @Order(15)
    @DisplayName("PersonRepository is interface")
    public void checkInterface() {
        assertTrue(PersonRepository.class.isInterface());
    }

}
