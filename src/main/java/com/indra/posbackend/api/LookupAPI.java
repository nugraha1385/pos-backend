package com.indra.posbackend.api;

import com.indra.posbackend.service.ConfigService;
import com.indra.posbackend.models.Lookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Indrap on 1/06/2018.
 */

@RestController
@RequestMapping("/lookup")
public class LookupAPI {

    private ConfigService configService;

    @Autowired
    public LookupAPI(ConfigService configService){
        this.configService = configService;
    }

    //findById
    @GetMapping(path = "/{id}")
    public HttpEntity<Lookup> findById(@PathVariable(name = "id") String id){
        Optional<Lookup> result = configService.getLookupRepository().findById(id);
        if (result.isPresent()){
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Lookup(), HttpStatus.BAD_REQUEST);
        }
    }
    //findByType
    @GetMapping(path = "/find/type")
    public HttpEntity<Lookup> findByType(@RequestParam(value = "type") String type){
        Lookup result = configService.findLookupByLookupCode(type);
        return new ResponseEntity<Lookup>(result, HttpStatus.OK);
    }

    //update
    @PutMapping(path = "/{id}")
    public HttpEntity<Lookup> update(@PathVariable(name="id") String id, @RequestBody Lookup lookup){
        Lookup result = configService.getLookupRepository().save(lookup);
        return new ResponseEntity<Lookup>(result, HttpStatus.OK);
    }
    //add
    @PostMapping(path = "/add")
    public HttpEntity<Lookup> add(@RequestBody Lookup lookup){
        Lookup result = configService.getLookupRepository().save(lookup);
        return new ResponseEntity<Lookup>(result,HttpStatus.OK);
    }
    //delete
}
