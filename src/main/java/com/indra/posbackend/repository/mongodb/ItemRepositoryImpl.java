package com.indra.posbackend.repository.mongodb;

import com.indra.posbackend.models.Item;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Created by Indrap on 19/07/2017.
 */
public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public long updateItem(String itemId, Item item) {
        //find the object
        Query query = new Query(Criteria.where("itemId").is(itemId));

        //prepare update
        Update update = new Update();
        update.set("code",item.getCode())
                .set("name",item.getName())
                .set("description",item.getDescription())
                .set("type",item.getType())
                .set("unit",item.getUnit())
                .set("rate",item.getRate())
                .set("tax",item.getTax())
                .set("note",item.getNote())
                .set("disabled",item.isDisabled());

        UpdateResult result = mongoTemplate.updateFirst(query,update,Item.class);
        //cek return value
        if (result != null)
            return result.getModifiedCount();
        else{
            logger.info("Failed to update item {0}", item.toString());
            return 0;
        }

    }
}


