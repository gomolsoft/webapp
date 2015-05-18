package de.moso.de.moso.repository

import de.moso.entity._
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by sandro on 18.05.15.
 */
trait IoTComponentRepository extends MongoRepository[SensorModule, String] {

}
