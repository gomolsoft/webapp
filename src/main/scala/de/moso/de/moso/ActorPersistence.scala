package de.moso.de.moso


import akka.actor.Actor
import de.moso.de.moso.repository._
import de.moso.entity.SensorModule

/**
 * Created by sandro on 02.06.15.
 */
class ActorPersistence(myComponentRepository: IoTComponentRepository) extends Actor {
  def receive = {
    case sm: SensorModule => myComponentRepository.save(sm)
    case _                =>
  }
}

