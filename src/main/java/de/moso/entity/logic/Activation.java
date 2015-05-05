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

}
