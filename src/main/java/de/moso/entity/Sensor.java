package de.moso.entity;

import java.util.List;

/**
 * Created by sandro on 26.04.15.
 */
public class Sensor<T> implements IotInterface {
    private String name;

    private SensorType sensorType;
    private List<SensorProperty> peroperies;

    public Sensor() {
    }

    public Sensor(String name, SensorType sensorType) {
        this.sensorType = sensorType;
        this.name = name;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public String getName() {
        return name;
    }
}
