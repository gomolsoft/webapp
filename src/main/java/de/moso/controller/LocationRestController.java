package de.moso.controller;

import de.moso.entity.Location;
import de.moso.repository.ComponentRepository;
import de.moso.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sandro on 05.05.15.
 */

@RestController
@RequestMapping("/location/old")
public class LocationRestController {
    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private LocationRepository locationRepository;

    @RequestMapping(value = "/room/{room}", method = RequestMethod.GET)
    public List<String> getSerialsByRoom(@PathVariable("room") String room) {
        final Location location = locationRepository.findByLocationName(room);
        if (location != null && location.getSerialNos() != null && !location.getSerialNos().isEmpty())
            return location.getSerialNos();
        return null;
    }

    @RequestMapping(value = "/device/{serialNo}", method = RequestMethod.GET)
    public Location getBySerialNo(@PathVariable("serialNo") String serialNo) {
        final Location location = locationRepository.findBySerialNo(serialNo);
        return location;
    }

}
