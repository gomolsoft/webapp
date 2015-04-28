package de.moso.entity;

import java.util.List;

/**
 * Created by sandro on 26.04.15.
 */
public class Sensor<T> implements IotInterface {
    private String name;

    private SensorType sensorType;
    private List<SensorProperty> properties;

    public Sensor() {
    }

    public Sensor(String name, SensorType sensorType, List<SensorProperty> properties) {
        this.sensorType = sensorType;
        this.name = name;
        this.properties = properties;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public String getName() {
        return name;
    }

    public List<SensorProperty> getProperties() {
        return properties;
    }
}
