package de.moso.factory;

import de.moso.entity.Actor;
import de.moso.entity.IoTProperty;
import de.moso.entity.ValueTypeProperty;
import de.moso.entity.typify.SerialNoTypifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandro on 26.04.15.
 */
@Component
public class ActorFactory {
    public List<Actor> createFromSerial(final String serial) {

        final List<Actor> actuators = new ArrayList<>();
        final SerialNoTypifier type = SerialNoTypifier.findBySerialNo(serial);

        switch (type) {
            case HEIZUNGREGLER: {
                List<IoTProperty> properties = new ArrayList<>();
                properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.NUMERIC));
                actuators.add(new Actor<Number>("Regler", properties));

                break;
            }

            case STECKDOSE: {
                List<IoTProperty> properties = new ArrayList<>();
                properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.BOOLEAN));
                actuators.add(new Actor<Number>("Relais", properties));

                break;
            }

            default:
                return null;
        }
        return actuators;
    }

}
