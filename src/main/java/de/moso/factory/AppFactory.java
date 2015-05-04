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
    public List<InternetData> createFromApiKey(final String serialNo) {
        final List<InternetData> internetList = new ArrayList<>();
        final SerialNoTypifier type = SerialNoTypifier.findBySerialNo(serialNo);

        switch (type) {
            case FORCAST_APP: {
                final List<IoTProperty> properties = new ArrayList<>();
                properties.add(new RangePropertie<>(-100.0F, 100.0F));
                properties.add(new UnityProperty("°C"));
                properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.NUMERIC));
                internetList.add(new InternetData<Number>("Wetter", properties));
            }
        }

        return internetList;
    }
}
