package de.moso.entity;

/**
 * Created by sandro on 26.04.15.
 */
public class Actor<T> implements IotInterface {
    private ValueTypeProperty valueTypeProperty;
    private String name;

    public Actor() {
    }

    public Actor(String name, ValueTypeProperty valueTypeProperty) {
        this.valueTypeProperty = valueTypeProperty;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
