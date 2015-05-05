package de.moso.factory;

import de.moso.entity.*;
import de.moso.entity.typify.SerialNoTypifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandro on 04.05.15.
 */
@Component
public class AppFactory {
    /**
     * ergebnis immer für eine spezielle App
     *
     * @param serialNo
     * @return alle eigenschaften der app selber.
     * Werte können so gebündelt werden und über die Propertie dynamisch definiert werden
     */
    public List<InternetData> createFromApiKey(final String serialNo) {
        final List<InternetData> internetList = new ArrayList<>();
        final SerialNoTypifier type = SerialNoTypifier.findBySerialNo(serialNo);

        switch (type) {

            case FORCAST_APP: {
                {
                    final List<IoTProperty> properties = new ArrayList<>();
                    properties.add(new RangePropertie<>(-100.0F, 100.0F));
                    properties.add(new UnityProperty("°C"));
                    properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.NUMERIC));

                    internetList.add(new InternetData<Number>("Temperatur", properties));
                }
                {
                    final List<IoTProperty> properties = new ArrayList<>();
                    properties.add(new RangePropertie<>(0, 100));
                    properties.add(new UnityProperty("%"));
                    properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.NUMERIC));

                    internetList.add(new InternetData<Number>("Regenwarscheindlichkeit", properties));
                }

                break;
            }

            default:
                return null;
        }

        return internetList;
    }
}
