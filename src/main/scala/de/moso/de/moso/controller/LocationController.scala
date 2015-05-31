package de.moso.de.moso.controller

import de.moso.de.moso.repository.IoTComponentRepository
import de.moso.entity._
import de.moso.entity.factory.ModuleFiller
import de.moso.repository.{ComponentRepository, LocationRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

import scala.collection.JavaConversions._

/**
 * Created by sandro on 17.05.15.
 */
@RestController
@RequestMapping(Array("/location"))
class LocationController {

  @Autowired var locationRepository: LocationRepository = _
  @Autowired var componentRepository: ComponentRepository = _
  @Autowired var myComponentRepository: IoTComponentRepository = _

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

  @RequestMapping(method = Array(RequestMethod.GET), value = Array("/test"))
  def test() = {
    val s = new SensorModule("1-4711", "Temperatur") with ModuleFiller

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

    myComponentRepository.save(s)

    val t = myComponentRepository.findAll()

    val a = new Array[Any](5)
    a.update(0, s)
    a.update(1, t)

    val lb = LogicBuilder(null, s.properties.get("Feuchtigkeit"))
    lb.build
    ResponseEntity.ok(a)
  }

  /*
  private[this] def addProperty(key: String, propertyType: String)(propVal: String, value: String)(properties: java.util.Map[String,java.util.List[IoTPropertyBase]]): Unit = {
    if (properties.get( key ) == null)
      properties.put( key, new java.util.ArrayList() )

    val iotPropList = properties.get(key)
    if (iotPropList isEmpty)
      iotPropList add( IoTPropertyBase(propertyType) )

    def assign:Boolean = {
      var found = false
      for (t <- iotPropList) {
        t match {
          case iot: IoTPropertyBase if (iot.propertyName.equals(propertyType)) => {
            iot.add(propVal, value)
            found = true
          }
          case _ =>
        }
      }
      found
    }

    if (!assign) {
      iotPropList add( IoTPropertyBase(propertyType) )
      assign
    }
  }
*/
}
