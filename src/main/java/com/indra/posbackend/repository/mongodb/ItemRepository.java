package com.indra.posbackend.repository.mongodb;


import com.indra.posbackend.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * Created by Indrap on 11/07/2017.
 */
public interface ItemRepository extends MongoRepository<Item,String>,ItemRepositoryCustom {

    Item findItemByCode(String code);
    Item findItemByItemId(String itemId);

    List<Item> findAll();
    List<Item> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCodeContainingIgnoreCase(String name, String description, String code);


}
