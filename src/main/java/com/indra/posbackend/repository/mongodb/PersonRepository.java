package com.indra.posbackend.repository.mongodb;

import com.indra.posbackend.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Indrap on 30/05/2018.
 */


public interface PersonRepository extends MongoRepository<Person,String> {
    List<Person> findByFirstName(@Param("firstName") String firstName);
}
