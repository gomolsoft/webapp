package de.moso.controller;

import de.moso.entity.*;
import de.moso.entity.logic.Activation;
import de.moso.entity.logic.Condition;
import de.moso.entity.logic.LogicBrick;
import de.moso.entity.logic.LogicNumericCondition;
import de.moso.entity.typify.SerialNoTypifier;
import de.moso.factory.ActorFactory;
import de.moso.factory.AppFactory;
import de.moso.factory.SensorFactory;
import de.moso.repository.ComponentRepository;
import de.moso.repository.LocationRepository;
import de.moso.repository.LogicRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sandro on 17.04.15.
 */
@RestController
@RequestMapping("/device")
public class DeviceRestController {
    @Autowired
    private ComponentRepository componentRepository;


    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LogicRepository logicRepository;

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public List<Component> getDevices() {
        return componentRepository.findAll(new Sort(Sort.Direction.ASC, "name", "serialNo"));
    }

    //    @RequestMapping(value = "/{serNo}/basicRegister", method = RequestMethod.POST)
    @RequestMapping(value = "/{serNo}/basicRegister", method = RequestMethod.POST)
    public List<Component> doDone(@PathVariable("serNo") String serialNo/*, @RequestBody Component comp*/) {
        final Component component = componentRepository.findBySerialNo(serialNo);
        component.setConfigMode(ConfigMode.CONFIGURING);
        componentRepository.save(component);
        return getDevices();
    }

    @RequestMapping(value = "/reinit", method = RequestMethod.GET)
    @Autowired
    public List<Component> doReinitialize(final SensorFactory sensorFactory, final ActorFactory actorFactory) {
        for (Component c : componentRepository.findAll()) {
            if (c.getConfigMode() == ConfigMode.CONFIGURING) {
                c.setConfigMode(ConfigMode.UNCONFIGURED);
                componentRepository.save(c);
            }
        }
        return getDevices();
    }

    @RequestMapping(value = "/{serialId}", method = RequestMethod.GET)
    public Component getSerialsByRoom(@PathVariable("serialId") String serialId) {
        final Component component = componentRepository.findBySerialNo(serialId);
        return component;
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public Number unreadMessages() {
        return new Integer(9);
    }

    @RequestMapping(value = "/bytype/{compType}", method = RequestMethod.GET)
    public List<Component> getComponentListOfType(@PathVariable("compType") String typeAsString) {
        List<Component> filtComponents = new ArrayList<>();
        for (Component component : getDevices()) {

            if (!CollectionUtils.isEmpty(component.getActors()))
                if (StringUtils.lowerCase(typeAsString).equalsIgnoreCase("actors"))
                    filtComponents.add(component);

            if (!CollectionUtils.isEmpty(component.getSensors()))
                if (StringUtils.lowerCase(typeAsString).equalsIgnoreCase("sensors"))
                    filtComponents.add(component);
        }
        return filtComponents;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    @Autowired
    public List<Component> doInit(final SensorFactory sensorFactory, final ActorFactory actorFactory, final AppFactory appFactory) {
        Actor actor;
        Component actorComponent;

        Sensor sensor;
        Component sensorComponent;


        componentRepository.deleteAll();
        logicRepository.deleteAll();
        locationRepository.deleteAll();

        final MultiMap<String, List<Component>> mhm = new MultiValueMap();

        Component c = new Component();
        c.setName("Lichtschalter");
        c.setSerialNo("123-123-000-" + SerialNoTypifier.INTELIGENTLIGHTSWITCH.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        c.setInternetDatas(appFactory.createFromApiKey(c.getSerialNo()));
        mhm.put("Korridor", c);
        final List<Component> components = new ArrayList<>();
        components.add(c);


        c = new Component();
        c.setName("Fenster/Tür-Sensor");
        c.setSerialNo("223-323-001-" + SerialNoTypifier.SWITCH.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        c.setInternetDatas(appFactory.createFromApiKey(c.getSerialNo()));
        mhm.put("Bad", c);
        components.add(c);

        c = new Component();
        c.setName("Heizung");
        c.setSerialNo("453-323-002-" + SerialNoTypifier.HEIZUNGREGLER.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        c.setInternetDatas(appFactory.createFromApiKey(c.getSerialNo()));
        mhm.put("Wohnzimmer", c);
        components.add(c);


        c = new Component();
        c.setName("Steckdose");
        c.setSerialNo("453-923-003-" + SerialNoTypifier.STECKDOSE.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        c.setInternetDatas(appFactory.createFromApiKey(c.getSerialNo()));
        mhm.put("Esszimmer", c);
        components.add(c);

        actor = c.getActors().get(0);
        actorComponent = c;


        c = new Component();
        c.setName("Bewegungsmelder");
        c.setSerialNo("523-523-004-" + SerialNoTypifier.BEWEGUNGSENSOR.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        c.setInternetDatas(appFactory.createFromApiKey(c.getSerialNo()));
        mhm.put("Korridor", c);
        components.add(c);

        c = new Component();
        c.setName("Bewegungsmelder");
        c.setSerialNo("553-523-005-" + SerialNoTypifier.BEWEGUNGSENSOR.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        c.setInternetDatas(appFactory.createFromApiKey(c.getSerialNo()));
        mhm.put("Küche", c);
        components.add(c);

        c = new Component();
        c.setName("Bewegungsmelder");
        c.setSerialNo("543-523-006-" + SerialNoTypifier.BEWEGUNGSENSOR.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        c.setInternetDatas(appFactory.createFromApiKey(c.getSerialNo()));
        mhm.put("Balkon", c);
        components.add(c);

        c = new Component();
        c.setName("RF-Id");
        c.setSerialNo("533-523-007-" + SerialNoTypifier.RFID.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        c.setInternetDatas(appFactory.createFromApiKey(c.getSerialNo()));
        mhm.put("Haustüre", c);
        components.add(c);

        c = new Component();
        c.setName("Bewegungsmelder");
        c.setSerialNo("533-523-008-" + SerialNoTypifier.BEWEGUNGSENSOR.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        c.setInternetDatas(appFactory.createFromApiKey(c.getSerialNo()));
        mhm.put("Küche", c);
        components.add(c);

        c = new Component();
        c.setName("Intelligenter-Schalter");
        c.setSerialNo("533-523-009-" + SerialNoTypifier.INTELIGENTLIGHTSWITCH.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        c.setInternetDatas(appFactory.createFromApiKey(c.getSerialNo()));
        mhm.put("Wohnzimmer", c);
        components.add(c);

        c = new Component();
        c.setName("Feuchtigkeitssensor");
        c.setSerialNo("533-523-010-" + SerialNoTypifier.HUMIDITY.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        c.setInternetDatas(appFactory.createFromApiKey(c.getSerialNo()));
        mhm.put("Bad", c);
        components.add(c);

        sensor = c.getSensors().get(0);
        sensorComponent = c;

        c = new Component();
        c.setName("Wetter");
        c.setSerialNo("533-523-011-" + SerialNoTypifier.FORCAST_APP.getSerialNoEnd());
        c.setConfigMode(ConfigMode.UNCONFIGURED);
        c.setSensors(sensorFactory.createFromSerial(c.getSerialNo()));
        c.setActors(actorFactory.createFromSerial(c.getSerialNo()));
        c.setInternetDatas(appFactory.createFromApiKey(c.getSerialNo()));
        components.add(c);

        componentRepository.save(components);

        List<Location> locations = new ArrayList<>();
        for (Map.Entry<String, Object> entrySet : mhm.entrySet()) {
            locations.add(new Location(entrySet.getKey(), (List<Component>) entrySet.getValue()));
        }

        locationRepository.save(locations);

        Condition input = new Condition(sensorComponent, sensor, 20, LogicNumericCondition.LT);
        Activation output = new Activation(actorComponent, actor, true);

        LogicBrick temperaturRegler = new LogicBrick(input, output);
        logicRepository.save(temperaturRegler);

        return components;
    }
}
