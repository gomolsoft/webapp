package de.moso.entity;


import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by sandro on 17.04.15.
 */
public class Component {
    @Id
    private String id;

    private String name;
    private String serialNo;

    private ConfigMode configMode;

    private List<Sensor> sensors;
    private List<Actor> actors;


    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNo() {
        return serialNo;
    }
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public ConfigMode getConfigMode() {
        return configMode;
    }

    public void setConfigMode(ConfigMode configMode) {
        this.configMode = configMode;
    }
}
