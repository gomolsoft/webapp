package de.moso.controller;

import de.moso.entity.Component;
import de.moso.entity.ConfigMode;
import de.moso.entity.typify.SerialNoTypifier;
import de.moso.factory.ActorFactory;
import de.moso.factory.SensorFactory;
import de.moso.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandro on 17.04.15.
 */
@RestController
@RequestMapping("/device")
public class WebRestController {
    @Autowired
    private CustomerRepository repository;

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public List<Component> getDevices() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "name", "serialNo"));
    }

    //    @RequestMapping(value = "/{serNo}/basicRegister", method = RequestMethod.POST)
    @RequestMapping(value = "/{serNo}/basicRegister", method = RequestMethod.POST)
    public List<Component> doDone(@PathVariable("serNo") String serialNo/*, @RequestBody Component comp*/) {
        final Component component = repository.findBySerialNo(serialNo);
        component.setConfigMode(ConfigMode.CONFIGURING);
        repository.save(component);
        return getDevices();
    }


    @RequestMapping(value = "/init", method = RequestMethod.GET)
    @Autowired
    public List<Component> doInit(final SensorFactory sensorFactory, final ActorFactory actorFactory) {
        repository.deleteAll();

        Component c = new Component();
        c.setName("Lichtschalter");
        c.setSerialNo("123-123-000-" + SerialNoTypifier.INTELIGENTLIGHTSWITCH.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        final List<Component> components = new ArrayList<>();
        components.add(c);

        c = new Component();
        c.setName("Fenster/Tür-Sensor");
        c.setSerialNo("223-323-001-" + SerialNoTypifier.SWITCH.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        components.add(c);

        c = new Component();
        c.setName("Heizung");
        c.setSerialNo("453-323-002-" + SerialNoTypifier.HEIZUNGREGLER.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        components.add(c);

        c = new Component();
        c.setName("Steckdose");
        c.setSerialNo("453-923-003-" + SerialNoTypifier.STECKDOSE.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        components.add(c);

        c = new Component();
        c.setName("Bewegungsmelder");
        c.setSerialNo("523-523-004-" + SerialNoTypifier.BEWEGUNGSENSOR.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        components.add(c);

        c = new Component();
        c.setName("Bewegungsmelder");
        c.setSerialNo("553-523-005-" + SerialNoTypifier.BEWEGUNGSENSOR.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        components.add(c);

        c = new Component();
        c.setName("Bewegungsmelder");
        c.setSerialNo("543-523-006-" + SerialNoTypifier.BEWEGUNGSENSOR.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        components.add(c);

        c = new Component();
        c.setName("RF-Id");
        c.setSerialNo("533-523-007-" + SerialNoTypifier.RFID.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        components.add(c);

        c = new Component();
        c.setName("Bewegungsmelder");
        c.setSerialNo("533-523-008-" + SerialNoTypifier.BEWEGUNGSENSOR.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        components.add(c);

        repository.save(components);

        return components;
    }

}
