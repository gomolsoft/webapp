package de.moso.de.moso


import akka.actor.Actor
import de.moso.de.moso.repository._
import de.moso.entity.{Location, Room, SensorModule}

/**
 * Created by sandro on 02.06.15.
 */
class ActorPersistence(myComponentRepository: IoTComponentRepository, myRoomRepository: IoTRoomRepository, myLocationRepository: IoTLocationRepository) extends Actor {
  def receive = {
    case sm: SensorModule => myComponentRepository.save(sm)
    case lo: Location     => myLocationRepository.save(lo)
    case ro: Room         => myRoomRepository.save(ro)
    case _                =>
  }
}

