package de.moso.entity.logic;

import de.moso.entity.Actor;
import de.moso.entity.Sensor;
import org.springframework.data.annotation.Id;

/**
 * Created by sandro on 04.05.15.
 */
public class LogicBrick {
    @Id
    private String id;

    private Sensor sensor;
    private Actor actor;
    private LogicCondition conditions;

    public LogicBrick() {
    }

    public LogicBrick(Sensor sensor, Actor actor, LogicCondition conditions) {
        this.sensor = sensor;
        this.actor = actor;
        this.conditions = conditions;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public LogicCondition getConditions() {
        return conditions;
    }

    public void setConditions(LogicCondition conditions) {
        this.conditions = conditions;
    }
}
