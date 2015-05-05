package de.moso.repository;

import de.moso.entity.Sensor;
import de.moso.entity.logic.LogicBrick;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by sandro on 04.05.15.
 */
public interface LogicRepository extends MongoRepository<LogicBrick, Sensor> {
    // findBySensor(Sensor sensor);
}