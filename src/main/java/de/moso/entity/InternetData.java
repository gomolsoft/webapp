package de.moso.entity;


import java.util.List;

/**
 * Created by sandro on 04.05.15.
 */

@InputElement
@OutputElement
public class InternetData<T> implements IotInterface {
    private String name;

    private List<IoTProperty> appPropertie;

    public InternetData(String name, List<IoTProperty> appPropertie) {
        super();
        this.name = name;
        this.appPropertie = appPropertie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IoTProperty> getAppPropertie() {
        return appPropertie;
    }

    public void setAppPropertie(List<IoTProperty> appPropertie) {
        this.appPropertie = appPropertie;
    }


}
