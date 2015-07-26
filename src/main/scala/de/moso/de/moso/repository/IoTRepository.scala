package de.moso.de.moso.repository

import de.moso.entity.{Location, Room, SensorModule}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by sandro on 18.05.15.
 */


trait IoTComponentRepository extends MongoRepository[SensorModule, String] {
  def findBySerialNo(serialNo: String): SensorModule

}

trait IoTRoomRepository extends MongoRepository[Room, String] {
  def findByName(name: String): Room

}


trait LocationRepository extends MongoRepository[Location, String]

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.{Criteria, Query}


class IoTLocationRepository extends LocationRepository {
  @Autowired var mongoTemplate: MongoTemplate = _

  def loadByRoom(room: Room) = {
    mongoTemplate.find(new Query(Criteria.where("room").is(room)), classOf[Location])
  }
}
