package de.moso.de.moso.repository

import de.moso.entity.{Location, Room, SensorModule}
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

trait IoTLocationRepository extends MongoRepository[Location, String] {
  def findByRoom(room: Room): Location

}