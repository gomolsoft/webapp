package de.moso.de.moso


import akka.actor.Actor
import de.moso.de.moso.repository._
import de.moso.entity.SensorModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
 * Created by sandro on 02.06.15.
 */
class ActorPersistence extends Actor {
  implicit val ctx = new AnnotationConfigApplicationContext(classOf[IoTComponentRepository])

  @Autowired var myComponentRepository: IoTComponentRepository = _

  def receive = {
    case sm: SensorModule => myComponentRepository.save(sm)
    case _                =>
  }
}

