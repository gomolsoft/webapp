package de.moso.entity;

import java.util.List;

/**
 * Created by sandro on 04.05.15.
 */
public class InternetData<T> implements IotInterface {
    private String name;

    private List<IoTProperty> appPropertie;

    public InternetData(String name, List<IoTProperty> appPropertie) {
        super();
        this.name = name;
        this.appPropertie = appPropertie;
    }

}
