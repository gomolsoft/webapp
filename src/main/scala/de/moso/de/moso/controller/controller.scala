package de.moso.de.moso.controller

import de.moso.de.moso.repository.IoTComponentRepository
import de.moso.entity._
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
    val location = locationRepository findByLocationName (room)
    if (location != null) {
      val components = {
        for {
          serialNo <- location getSerialNos()
          component = componentRepository findBySerialNo (serialNo)
        } yield component
      }
      ResponseEntity.ok(components.toArray)
    } else {
      new ResponseEntity(Array.empty, HttpStatus.NOT_FOUND)
    }
  }

  @RequestMapping(method = Array(RequestMethod.GET), value = Array("/rooms"))
  def test123() = {
    locationRepository findAll()
  }

  @RequestMapping(method = Array(RequestMethod.GET), value = Array("/test"))
  def test() = {

    val s = new SensorModule("1-4711", "Temperatur")

    var range = s.addProperty("Temperatur", "Range")_
    range("RangeMin", "1")
    range("RangeMax", "199")

    var value = s.addProperty("Temperatur", "Value")_
    value("Type", "Float")


    range = s.addProperty("Feuchtigkeit", "Range")_
    range("RangeMin", "0")
    range("RangeMax", "999")

    value = s.addProperty("Feuchtigkeit", "Value")_
    value("Type", "Int")

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

    /*
        val u = new User("UserName", "UserPwd","Admin", "AdminLast")

        def coder(mainCode:String) = {"coder->"+mainCode+u.getUsername+":"+ UUID.randomUUID().toString}

        val s = new Sensor[Any]("Sensor", null)

        val c = new MyComponent(coder)
        c.name = "MyComponent"

        val tc = new TestClass("TestClass", coder)

        val id = UUID.randomUUID().toString

        for {
          x <- 0 to 9
          y <- 0 to 9
        }
        {
          val dbComp = new DBMyComponent(s"$x-$id-$y", s"$x:$y")
          myComponentRepository.save(dbComp)
        }

        val t = myComponentRepository.findAll()

        val a = new Array[Any](5)
        a.update(0,c)
        a.update(1,s)
        a.update(2,u)
        a.update(3,tc)
        a.update(4,t)

        ResponseEntity.ok(a)
        */
  }

}
