package de.moso.entity;


import java.util.List;

/**
 * Created by sandro on 26.04.15.
 */
@OutputElement
public class Actor<T> implements IotInterface {
    private List<IoTProperty> properies;
    private String name;

    public Actor() {
    }

    public Actor(String name, List<IoTProperty> properties) {
        this.properies = properties;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<IoTProperty> getProperies() {
        return properies;
    }

}
