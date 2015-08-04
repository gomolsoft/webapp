package de.moso.de.moso.controller

import java.security.Principal
import java.util

import de.moso.de.moso.repository.IoTComponentRepository
import de.moso.entity.SensorModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation._

/**
 * Created by sandro on 27.05.15.
 */
@RestController
@RequestMapping(Array("/device"))
class DeviceController {
  @Autowired var myComponentRepository: IoTComponentRepository = _

  @RequestMapping(value = Array("/{serialId}"), produces = Array("application/json"), method = Array(RequestMethod.GET))
  def detectBySerialNo(@PathVariable("serialId") serialId: String) = {
    val device = myComponentRepository findBySerialNo (serialId)
    device
  }

  @PreAuthorize("hasRole('ROLE_DOMAIN_USER')")
  @RequestMapping(value = Array("/devices"), produces = Array("application/json"), method = Array(RequestMethod.GET))
  def allDevices(user: Principal): util.List[SensorModule] = {
    myComponentRepository findAll
  }

}
