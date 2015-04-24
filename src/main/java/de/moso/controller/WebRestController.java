package de.moso.controller;

import de.moso.entity.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandro on 17.04.15.
 */
@RestController
@RequestMapping("/device")
public class WebRestController {

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public List<Component> getDevices() {
        Component c = new Component();
        c.setName("Lichtschalter");
        c.setSerialNo("123-123-123-123");
        List<Component> components = new ArrayList<>();
        components.add(c);

        c = new Component();
        c.setName("Türöffner");
        c.setSerialNo("223-323-423-523");
        components.add(c);

        c = new Component();
        c.setName("Heizung");
        c.setSerialNo("453-323-423-523");
        components.add(c);

        c = new Component();
        c.setName("Steckdose");
        c.setSerialNo("453-323-423-523");
        components.add(c);

        c = new Component();
        c.setName("Bewegungsmelder");
        c.setSerialNo("523-523-423-523");
        components.add(c);

        return components;
    }

    @RequestMapping(value = "/{serNo}/done", method = RequestMethod.POST)
    public List<Component> doDone(@PathVariable("serNo") String serialNo, @RequestBody Component comp) {
        return new ArrayList<>();

    }

}
