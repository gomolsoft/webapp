package de.moso.factory;

import de.moso.entity.Actor;
import de.moso.entity.typify.ActorType;
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
            case HEIZUNGREGLER:

                actuators.add(new Actor<Number>("Regler", ActorType.NUMERIC));
                break;

            case STECKDOSE:
                actuators.add(new Actor<Number>("Relais", ActorType.SWITCH));
                break;


            default:
                return null;
            //throw new SensorConfigurationException("No Sensor-Type found for Serial No:"+serial);
        }
        return actuators;
    }

}
