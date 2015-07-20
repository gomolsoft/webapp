package de.moso.de.moso.controller

import javax.annotation.PostConstruct

import akka.actor.{ActorRef, ActorSystem, Props}
import de.moso.de.moso.ActorPersistence
import de.moso.de.moso.repository.IoTComponentRepository
import de.moso.entity._
import de.moso.entity.factory.ModuleFiller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation._

/**
 * Created by sandro on 17.05.15.
 */
@RestController
@RequestMapping(Array("/location"))
class LocationController {

  //@Autowired var locationRepository: LocationRepository = _
  //@Autowired var componentRepository: ComponentRepository = _
  @Autowired var myComponentRepository: IoTComponentRepository = _

  val akkaSystem = ActorSystem("PersistenceSystem")
  var persistenceSystem: ActorRef = _

  @PostConstruct
  def init = {
    persistenceSystem = akkaSystem.actorOf(Props(classOf[ActorPersistence], myComponentRepository), name = "PersistenceSystem")
  }

  /*
  @RequestMapping(produces = Array("application/json"), method = Array(RequestMethod.GET), value = Array("/room/{room}"))
  def detectRoom(@PathVariable("room") room: String) = {
    val location = locationRepository findByLocationName room
    if (location != null) {
      val components = {
        for {
          serialNo <- location getSerialNos()
          component = componentRepository findBySerialNo serialNo
        } yield component
      }
      ResponseEntity.ok(components.toArray)
    } else {
      new ResponseEntity(Array.empty, HttpStatus.NOT_FOUND)
    }
  }

  @RequestMapping(method = Array(RequestMethod.GET), value = Array("/rooms"))
  def findAllRooms() = {
    ResponseEntity ok locationRepository.findAll()
  }
*/

  @RequestMapping(method = Array(RequestMethod.GET), value = Array("/test"))
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

    val t = myComponentRepository.findAll()

    val a = new Array[Any](5)
    a.update(0, s)
    a.update(1, t)

    val lb = LogicBuilder(null, s.properties.get("Feuchtigkeit"))
    lb.build

    ResponseEntity.ok(a)
  }
}
