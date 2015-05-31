package de.moso.de.moso.controller

import de.moso.de.moso.repository.IoTComponentRepository
import de.moso.repository.{ComponentRepository, LocationRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

/**
 * Created by sandro on 27.05.15.
 */
@RestController
@RequestMapping(Array("/myDevice"))
class DeviceController {
  @Autowired var locationRepository: LocationRepository = _
  @Autowired var componentRepository: ComponentRepository = _
  @Autowired var myComponentRepository: IoTComponentRepository = _

  @RequestMapping(value = Array("/{serialId}"), produces = Array("application/json"), method = Array(RequestMethod.GET))
  def detectBySerialNo(@PathVariable("serialId") serialId: String) = {
    val device = myComponentRepository findBySerialNo (serialId)
    device
  }

  @RequestMapping(value = Array("/devices"), produces = Array("application/json"), method = Array(RequestMethod.GET))
  def allDevices = {
    myComponentRepository findAll
  }

}
