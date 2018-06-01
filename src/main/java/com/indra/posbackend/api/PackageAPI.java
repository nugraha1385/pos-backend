package com.indra.posbackend.api;

import com.indra.posbackend.service.SaleableService;
import com.indra.posbackend.models.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Indrap on 9/08/2017.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/package")
public class PackageAPI {

    private SaleableService saleableService;

    @Autowired
    public PackageAPI(SaleableService saleableService){
        this.saleableService = saleableService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public HttpEntity<List<Package>> findAllItem(){
        List<Package> listPackage = saleableService.getPackageRepository().findAll();
        listPackage.parallelStream().forEach( aPackage -> aPackage.breakItems());
        return new ResponseEntity<>(listPackage, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<Package> findPackageByPackageId(
            @PathVariable(value="id") String id){

        Optional<Package> result = saleableService.getPackageRepository().findById(id);
        if (result.isPresent()){
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Package(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/find/code", method = RequestMethod.GET)
    public HttpEntity<Package> findPackageByCode(
            @RequestParam(value="code",required = true) String code){
        Package result = saleableService.getPackageRepository().findPackageByCode(code);
        result.breakItems();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
