package com.indra.posbackend.service;

import com.indra.posbackend.repository.mongodb.ItemRepository;
import com.indra.posbackend.repository.mongodb.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Indrap on 17/05/2018.
 */
@Service
public class SaleableService {

    private ItemRepository itemRepository;
    private PackageRepository packageRepository;

    @Autowired
    public SaleableService(ItemRepository itemRepository, PackageRepository packageRepository){
        this.itemRepository = itemRepository;
        this.packageRepository = packageRepository;
    }

    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    public PackageRepository getPackageRepository() {
        return packageRepository;
    }




}
