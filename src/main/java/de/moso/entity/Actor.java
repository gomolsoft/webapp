package de.moso.entity;

import de.moso.entity.typify.ActorType;

/**
 * Created by sandro on 26.04.15.
 */
public class Actor<T> implements IotInterface {
    private ActorType actorType;
    private String name;

    public Actor() {
    }

    public Actor(String name, ActorType actorType) {
        this.actorType = actorType;
        this.name = name;
    }

    public ActorType getActorType() {
        return actorType;
    }

    public void setActorType(ActorType actorType) {
        this.actorType = actorType;
    }

    public String getName() {
        return name;
    }
}
