package de.moso.entity;

/**
 * Created by sandro on 28.04.15.
 */
public class ValueTypeProperty implements IoTProperty {
    final String VALUEPROPERTY = "VALUEPROPERTY";

    private ValueTypePropertyType valuePropertyType;
    private String name = VALUEPROPERTY;

    public ValueTypeProperty() {
        valuePropertyType = ValueTypePropertyType.NUMERIC;
    }

    public ValueTypeProperty(final ValueTypePropertyType valuePropertyType) {
        this.valuePropertyType = valuePropertyType;
    }

    @Override
    public String getName() {
        return name;
    }

    public ValueTypePropertyType getValuePropertyType() {
        return valuePropertyType;
    }

    public void setValuePropertyType(ValueTypePropertyType valuePropertyType) {
        this.valuePropertyType = valuePropertyType;
    }

    public enum ValueTypePropertyType {
        NUMERIC, SEQUENCE, STRING, BOOLEAN
    }
}
