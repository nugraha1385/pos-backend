package com.indra.posbackend.api;

import com.indra.posbackend.service.SaleableService;
import com.indra.posbackend.models.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

;

/**
 * Created by Indrap on 11/07/2017.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/item")
public class ItemAPI {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SaleableService saleableService;

    @Autowired
    public ItemAPI(SaleableService saleableService){
        this.saleableService = saleableService;
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<Item> findItemByItemId(@PathVariable(required = true) String id){

        Item item = saleableService.getItemRepository().findItemByItemId(id);
        if (item != null){
            return new ResponseEntity<>(item, HttpStatus.OK);
        }else{
            logger.info("not found by item id =",id);
            return new ResponseEntity<>(new Item(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/find/code", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<Item> findItemByCode(
            @RequestParam(value="code",required = true) String code){
        Item item = saleableService.getItemRepository().findItemByCode(code);
        if (item != null){
            return new ResponseEntity<>(item, HttpStatus.BAD_REQUEST);
        } else {
            logger.info("not found by code =",code);
            return new ResponseEntity<>(new Item(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/find/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<List<Item>>findAllItem(){
       List<Item> listItem =  saleableService.getItemRepository().findAll();
       if (listItem != null){
           return new ResponseEntity<>(listItem, HttpStatus.OK);
       } else {
           logger.info("not found result");
           return new ResponseEntity<>(new ArrayList<Item>(), HttpStatus.BAD_REQUEST);
       }

    }

    @GetMapping(path = "/find/keyword", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<List<Item>>findByKeyword(
            @RequestParam(value="keyword",required = true) String keyword){
        List<Item> listItem =  saleableService.getItemRepository().findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCodeContainingIgnoreCase (keyword,keyword,keyword);
        if (listItem != null) {
            return new ResponseEntity<>(listItem, HttpStatus.OK);
        } else {
            logger.info(keyword);
            return new ResponseEntity<>(new ArrayList<Item>(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<Item>updateItem(
            @PathVariable String id,
            @RequestBody(required = true) Item item){
        long resultCode = saleableService.getItemRepository().updateItem(id,item);
        if (resultCode != 0){
            return new ResponseEntity<>(saleableService.getItemRepository().findItemByItemId(id), HttpStatus.OK);
        }else{
            logger.error("Not found item that need to be updated, item = {}, itemId", item, id);
            return new ResponseEntity<>(item, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/add")
    public HttpEntity<Item>insertItem(
            @RequestBody Item item){

        Item foundItem = saleableService.getItemRepository().findItemByCode(item.getCode());
        if (foundItem != null) {
            logger.error("Not found item that need to be updated, item = {}", item);
            return new ResponseEntity<>(item, HttpStatus.BAD_REQUEST);
        }

        item.setItemId(null);
        Item savedItem = saleableService.getItemRepository().save(item);
        return new ResponseEntity<>(savedItem, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public HttpEntity<String>deleteItem(
            @PathVariable String id){

        Example<Item> itemExample = Example.of(new Item(id));
        if (! saleableService.getItemRepository().exists(itemExample)){
            logger.error("Not found item that need to be deleted, itemId = {}", id);
            return new ResponseEntity<>(id,HttpStatus.PRECONDITION_FAILED);
        }

        saleableService.getItemRepository().deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
