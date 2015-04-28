package de.moso.factory;

import de.moso.entity.Sensor;
import de.moso.entity.SensorType;
import de.moso.entity.typify.SerialNoTypifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandro on 26.04.15.
 */
@Component
public class SensorFactory {

    public List<Sensor> createFromSerial(final String serial) {

        final List<Sensor> sensors = new ArrayList<>();
        final SerialNoTypifier type = SerialNoTypifier.findBySerialNo(serial);

        switch (type) {
            case DISTANCE:
                sensors.add(new Sensor<Number>("Distanz", SensorType.NUMERIC));
                break;

            case HUMIDITY:
                sensors.add(new Sensor<Number>("Feuchtigkeit", SensorType.NUMERIC));
                sensors.add(new Sensor<Number>("Temperatur", SensorType.NUMERIC));
                break;

            case INTELIGENTLIGHTSWITCH:
                sensors.add(new Sensor<Number>("Schalter", SensorType.NUMERIC));
                sensors.add(new Sensor<Number>("Temperatur", SensorType.NUMERIC));
                break;

            case SWITCH:
                sensors.add(new Sensor<Boolean>("Schalter", SensorType.SWITCH));
                break;

            case TEMPERATURE:
                sensors.add(new Sensor<Number>("Temperatur", SensorType.NUMERIC));
                break;

            case BEWEGUNGSENSOR:
                sensors.add(new Sensor<Number>("Bewegungadetektor", SensorType.SWITCH));
                break;

            case RFID:
                sensors.add(new Sensor<String>("RFID", SensorType.RFID));
                break;

            default:
                return null;
            //throw new SensorConfigurationException("No Sensor-Type found for Serial No:"+serial);
            //throw new SensorConfigurationException("No Sensor-Type found for Serial No:"+serial);
        }
        return sensors;
    }

}
