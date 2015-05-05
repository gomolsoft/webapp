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
    private LogicCondition conditions;

    public Condition() {
    }

    public Condition(Component component, IotInterface iotInterface, T conditionValue, LogicCondition conditions) {
        this.component = component;
        this.iotInterface = iotInterface;
        this.conditionValue = conditionValue;
        this.conditions = conditions;
    }

}
