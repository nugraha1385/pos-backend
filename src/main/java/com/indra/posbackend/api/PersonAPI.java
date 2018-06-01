package com.indra.posbackend.api;

import com.indra.posbackend.models.Person;
import com.indra.posbackend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Indrap on 1/06/2018.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/person")
public class PersonAPI {

    private PersonService personService;

    @Autowired
    public PersonAPI(PersonService personService){
        this.personService = personService;
    }

    //findById
    @GetMapping(name = "/{id}")
    public HttpEntity<Person> findById(@PathVariable(name = "id") String id){
        Optional<Person> result = personService.getPersonRepository().findById(id);
        if (result.isPresent()){
            return new ResponseEntity<Person>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Person>(new Person(), HttpStatus.NOT_FOUND);
        }
    }

    //findById
    @GetMapping(name = "/find/all}")
    public HttpEntity<List<Person>> findAll(){
        List<Person> result = personService.getPersonRepository().findAll();
        if (!result.isEmpty()){
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<Person>(), HttpStatus.NOT_FOUND);
        }
    }
    //save
    @PutMapping(name = "/{id}")
    public HttpEntity<Person> save(@PathVariable(name = "id") String id, @RequestBody Person person){
        Person result = personService.getPersonRepository().save(person);
        return new ResponseEntity<Person>(result, HttpStatus.OK);
    }

    //add
    @PostMapping(name = "/add")
    public HttpEntity<Person> save(@RequestBody Person person){
        Person result = personService.getPersonRepository().save(person);
        return new ResponseEntity<Person>(result, HttpStatus.OK);
    }

    //delete
    @DeleteMapping(name = "/{id}")
    public HttpEntity<String> save(@PathVariable(name = "id") String id ){
        personService.getPersonRepository().deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
