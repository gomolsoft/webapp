package de.moso.entity;

/**
 * Created by sandro on 28.04.15.
 */
public class UnityProperty implements IoTProperty {
    static final String NAME = "Einheit";

    private String name;
    private String unityName;

    public UnityProperty() {
        name = NAME;
    }

    public UnityProperty(String unityName) {
        this();
        this.unityName = unityName;
    }

    public UnityProperty(String name, String unityName) {
        this.name = name;
        this.unityName = unityName;
    }

    public String getUnityName() {
        return unityName;
    }

    public void setUnityName(String unityName) {
        this.unityName = unityName;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
