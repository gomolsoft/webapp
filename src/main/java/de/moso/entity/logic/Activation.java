package de.moso.entity.logic;

import de.moso.entity.Component;
import de.moso.entity.IotInterface;

/**
 * Created by sandro on 05.05.15.
 */
public class Activation<T extends Object> {
    private Component component;
    private IotInterface iotInterface;

    private T conditionValue;

    public Activation() {
    }

    public Activation(Component component, IotInterface iotInterface, T conditionValue) {
        this.component = component;
        this.iotInterface = iotInterface;
        this.conditionValue = conditionValue;
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
}
