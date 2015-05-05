package de.moso.repository;

import de.moso.entity.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Created by sandro on 04.05.15.
 */
public interface LocationRepository extends MongoRepository<Location, String> {
    Location findByLocationName(String locationName);

    @Query(value = "{'serialNos': ?0}")
        // 453-923-003-501
    Location findBySerialNo(String serialNos);
}
