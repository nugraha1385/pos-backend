package com.indra.posbackend.repository.mongodb;

import com.indra.posbackend.models.Package;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Indrap on 9/08/2017.
 */
public interface PackageRepository extends MongoRepository<Package, String> {
    Package findPackageByCode(String code);
}