package de.moso.repository;

import de.moso.entity.Component;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComponentRepository extends MongoRepository<Component, String> {

    Component findBySerialNo(String serialNo);

    //List<Component> findByName(String name);

}