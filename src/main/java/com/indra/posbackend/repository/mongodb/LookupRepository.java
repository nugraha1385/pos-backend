package com.indra.posbackend.repository.mongodb;

import com.indra.posbackend.models.Lookup;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Indrap on 23/05/2018.
 */
public interface LookupRepository extends MongoRepository<Lookup,String> {

    List<Lookup> findLookupByLookupType(String type);

}
