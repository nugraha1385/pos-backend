package com.indra.posbackend.service;

import com.indra.posbackend.models.Lookup;
import com.indra.posbackend.repository.mongodb.LookupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Indrap on 23/05/2018.
 */

@Service
public class ConfigService {
    Logger logger = LoggerFactory.getLogger(ConfigService.class);

    private LookupRepository lookupRepository;

    @Autowired
    public ConfigService() {
    }

    public Lookup findLookupByLookupCode(String type){
        List<Lookup> lookups = lookupRepository.findLookupByLookupType(type);
        if (lookups != null  && !lookups.isEmpty()){
            if(lookups.size() > 1){
                logger.error("Duplicate lookup type on ", type);
            }
            return lookups.get(0);
        } else {
            return null;
        }
    }


    public LookupRepository getLookupRepository() {
        return lookupRepository;
    }
}
