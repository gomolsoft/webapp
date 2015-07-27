package de.moso.de.moso.controller

import javax.annotation.PostConstruct

import akka.actor.{ActorRef, ActorSystem, Props}
import de.moso.de.moso.ActorPersistence
import de.moso.de.moso.repository.{IoTComponentRepository, IoTLocationRepository, IoTRoomRepository}
import de.moso.entity._
import de.moso.entity.factory.ModuleFiller
import de.moso.entity.finding.Tag
import de.moso.entity.naming.Description
import de.moso.logic.LogicBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

import scala.beans.BeanProperty


case class ModuleLocationable(
                           @BeanProperty var serialNo: String,
                           @BeanProperty var description: Description,
                           @BeanProperty var name:     String,
                           @BeanProperty var active:   Boolean,
                           @BeanProperty var room:     Room,
                           @BeanProperty var location: String) extends Module with Locationable

object ModuleLocationable {
  def apply(module: Module, location: Locationable) = {
    new ModuleLocationable(module.serialNo, module.description, module.name, module.active, location.room,location.location)
  }
}

/**
 * Created by sandro on 17.05.15.
 */
@RestController
@RequestMapping(Array("/location"))
class LocationController {
  @Autowired var myComponentRepository: IoTComponentRepository = _
  @Autowired var myLocationRepository: IoTLocationRepository = _
  @Autowired var myRoomRepository: IoTRoomRepository = _

  val akkaSystem = ActorSystem("PersistenceSystem")
  var persistenceSystem: ActorRef = _

  @PostConstruct
  def init = {
    persistenceSystem = akkaSystem.actorOf(Props(classOf[ActorPersistence]
      , myComponentRepository
      , myRoomRepository
      , myLocationRepository), name = "PersistenceSystem")
  }

  @RequestMapping(method = Array(RequestMethod.GET), value = Array("/room/{room}"))
  def detectRoom(@PathVariable("room") roomName: String) = {
    val room = myRoomRepository.findByName(roomName)

    if (room != null) {
      val locations = myLocationRepository.findByRoom(room)
      ResponseEntity.ok(locations)
    } else {
      new ResponseEntity(Array.empty, HttpStatus.NOT_FOUND)
    }
  }

  @RequestMapping(method = Array(RequestMethod.GET), value = Array("/rooms"))
  def findAllRooms() = {
    ResponseEntity.ok(myRoomRepository.findAll())
  }


  @RequestMapping(produces = Array("application/json"), method = Array(RequestMethod.GET), value = Array("/test"))
  def test() = {
    val s = new SensorModule("Temperatur", "1-4711", Description("Temperatur-Sensor"), true) with ModuleFiller


    var range = s.createPropertyType("Temperatur", "Range")_
    s.addProperty(range("RangeMin", "1"))
    s.addProperty(range("RangeMax", "199"))

    var value = s.createPropertyType("Temperatur", "Value")_
    s.addProperty (value("Type", "Float"))

    range = s.createPropertyType("Feuchtigkeit", "Range")
    s.addProperty (range("RangeMin", "0"))
    s.addProperty (range("RangeMax", "999"))

    value = s.createPropertyType("Feuchtigkeit", "Value")
    s.addProperty (value("Type", "Int"))

    s.addTags(Tag("Feuchtigkeit"))
    s.addTags(Tag("Temperatur"))

    persistenceSystem ! s
    //myComponentRepository.save(s)

    val r = Room("Schlafzimmer")
    persistenceSystem ! r

    val l = Location(r, "Fenster Links")
    persistenceSystem ! l

    var h2 = ModuleLocationable(s, l)
    //var t = mLocation(s)

    val t = myComponentRepository.findAll()

    val a = new Array[Any](5)
    a.update(0, s)
    a.update(1, t)

    a.update(3, h2)

    val lb = LogicBuilder(null, s.properties.get("Feuchtigkeit"))
    lb.build

    ResponseEntity.ok(a)
  }
}
