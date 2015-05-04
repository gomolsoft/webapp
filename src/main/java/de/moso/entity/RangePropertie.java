package de.moso.entity;

/**
 * Created by sandro on 28.04.15.
 */
public class RangePropertie<T> implements IoTProperty {
    static final String NAME = "Intervall";

    private T rangeFrom;
    private T rangeTo;
    private String name;

    public RangePropertie() {
        name = NAME;
    }

    public RangePropertie(T rangeFrom, T rangeTo) {
        this();
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
    }

    public RangePropertie(T rangeFrom, T rangeTo, String name) {
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(T rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public T getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(T rangeTo) {
        this.rangeTo = rangeTo;
    }
}
