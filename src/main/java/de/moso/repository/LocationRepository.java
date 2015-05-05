package de.moso.repository;

import de.moso.entity.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by sandro on 04.05.15.
 */
public interface LocationRepository extends MongoRepository<Location, String> {
    //Location findByID(String id);
}
