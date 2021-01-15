package com.espark.adarsh.controller;


import com.espark.adarsh.document.Person;
import com.espark.adarsh.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService service;


    @PostMapping("/person")
    public ResponseEntity createPerson(@RequestBody Person person) throws Exception {
        return new ResponseEntity(service.createPersonDocument(person), HttpStatus.CREATED);
    }

    @PutMapping("/person")
    public ResponseEntity updatePerson(@RequestBody Person person) throws Exception {
        return new ResponseEntity(service.updatePerson(person), HttpStatus.CREATED);
    }

    @GetMapping("/person/{id}")
    public Person findById(@PathVariable String id) throws Exception {
        return service.findById(id);
    }

    @GetMapping("/person")
    public List<Person> findAll() throws Exception {
        return service.findAll();
    }

    @DeleteMapping("/person/{id}")
    public String deletePersonDocument(@PathVariable String id) throws Exception {
        return service.deletePersonDocument(id);
    }

    @GetMapping(value = "/search")
    public List<Person> search(@RequestParam(value = "technology") String technology) throws Exception {
        return service.searchByTechnology(technology);
    }

    @GetMapping(value = "/api/v1/profiles/name-search")
    public List<Person> searchByName(@RequestParam(value = "name") String name) throws Exception {
        return service.findPersonByName(name);
    }

}
