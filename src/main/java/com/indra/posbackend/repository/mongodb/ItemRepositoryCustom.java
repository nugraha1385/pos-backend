package com.indra.posbackend.repository.mongodb;

import com.indra.posbackend.models.Item;

/**
 * Created by Indrap on 19/07/2017.
 */
public interface ItemRepositoryCustom {
    long updateItem(String itemId, Item item);
}
