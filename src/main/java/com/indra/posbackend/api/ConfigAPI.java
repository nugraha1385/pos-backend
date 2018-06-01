package com.indra.posbackend.api;

import com.indra.posbackend.service.ConfigService;
import com.indra.posbackend.models.Lookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Indrap on 23/05/2018.
 */

@RestController
@RequestMapping("/config")
public class ConfigAPI {

    private ConfigService configService;

    @Autowired
    public ConfigAPI(ConfigService configService){
        this.configService = configService;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public HttpEntity<Lookup> findById(@PathVariable(value = "id") String id){

        Optional<Lookup> lookup = configService.getLookupRepository().findById(id);
        if (lookup.isPresent()){
            return new ResponseEntity<>(lookup.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Lookup(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/find/all",method = RequestMethod.GET)
    public HttpEntity<List<Lookup>> findAll(){
        List<Lookup> listLookup = configService.getLookupRepository().findAll();
        return new ResponseEntity<>(listLookup,HttpStatus.OK);
    }

    @RequestMapping(value="/find/type",method = RequestMethod.GET)
    public HttpEntity<Lookup> findByType(@RequestParam(value = "type",required = true) String type){
        Lookup lookup = configService.findLookupByLookupCode(type);
        return new ResponseEntity<>(lookup,HttpStatus.OK);
    }


    //save
    //delete
    //edit
}
