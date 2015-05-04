package de.moso.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sandro on 26.04.15.
 */
public class Sensor<T> implements IotInterface, Serializable {
    private String name;

    private List<IoTProperty> properties;

    public Sensor() {
    }

    public Sensor(String name, List<IoTProperty> properties) {
        this.name = name;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public List<IoTProperty> getProperties() {
        return properties;
    }
}
