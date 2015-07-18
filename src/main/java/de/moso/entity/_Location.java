package de.moso.entity;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandro on 04.05.15.
 */
public class _Location {
    public String locationName;
    public List<String> serialNos;
    @Id
    private String id;

    public _Location() {
    }

    public _Location(String locationName, List<Component> components) {
        serialNos = new ArrayList<>();
        for (Component component : components) {
            serialNos.add(component.getSerialNo());
        }
        this.locationName = locationName;
    }

    public _Location(String locationName) {
        this.locationName = locationName;
        this.serialNos = serialNos;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<String> getSerialNos() {
        return serialNos;
    }

    public void setSerialNos(List<String> serialNos) {
        this.serialNos = serialNos;
    }

}
