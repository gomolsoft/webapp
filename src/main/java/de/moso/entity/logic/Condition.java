package de.moso.entity.logic;

import de.moso.entity.Component;
import de.moso.entity.IotInterface;

/**
 * Created by sandro on 05.05.15.
 */
public class Condition<T extends Object> {
    private Component component;
    private IotInterface iotInterface;

    private T conditionValue;
    private LogicCondition condition;

    public Condition() {
    }

    public Condition(Component component, IotInterface iotInterface, T conditionValue, LogicCondition condition) {
        this.component = component;
        this.iotInterface = iotInterface;
        this.conditionValue = conditionValue;
        this.condition = condition;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public IotInterface getIotInterface() {
        return iotInterface;
    }

    public void setIotInterface(IotInterface iotInterface) {
        this.iotInterface = iotInterface;
    }

    public T getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(T conditionValue) {
        this.conditionValue = conditionValue;
    }

    public LogicCondition getCondition() {
        return condition;
    }

    public void setCondition(LogicCondition condition) {
        this.condition = condition;
    }
}
