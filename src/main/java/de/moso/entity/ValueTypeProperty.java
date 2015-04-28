package de.moso.entity;

/**
 * Created by sandro on 28.04.15.
 */
public class ValueTypeProperty implements SensorProperty {
    private ValueTypePropertyType valuePropertyType;
    private String name;

    public ValueTypeProperty() {
        valuePropertyType = ValueTypePropertyType.NUMERIC;
        name = valuePropertyType.name();
    }

    public ValueTypeProperty(final ValueTypePropertyType valuePropertyType) {
        this.valuePropertyType = valuePropertyType;
        name = valuePropertyType.name();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.valuePropertyType = ValueTypePropertyType.valueOf(name);
        this.name = valuePropertyType.name();
    }

    public enum ValueTypePropertyType {
        NUMERIC, SEQUENCE, STRING, BOOLEAN
    }
}
