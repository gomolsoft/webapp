package de.moso.factory;

import de.moso.entity.*;
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
            case DISTANCE: {
                List<SensorProperty> properties = new ArrayList<>();
                properties.add(new RangePropertie<Integer>(0, 255));
                properties.add(new UnityProperty("cm"));
                properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.NUMERIC));

                sensors.add(new Sensor<Number>("Distanz", SensorType.NUMERIC, properties));
                break;
            }

            case HUMIDITY: {
                {
                    List<SensorProperty> properties = new ArrayList<>();
                    properties.add(new RangePropertie<Integer>(0, 255));
                    properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.NUMERIC));

                    sensors.add(new Sensor<Number>("Feuchtigkeit", SensorType.NUMERIC, properties));
                }

                {
                    List<SensorProperty> properties = new ArrayList<>();
                    properties.add(new RangePropertie<Integer>(0, 65000));
                    properties.add(new UnityProperty("^C"));
                    properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.NUMERIC));

                    sensors.add(new Sensor<Number>("Temperatur", SensorType.NUMERIC, properties));
                }
                break;
            }

            case INTELIGENTLIGHTSWITCH: {
                {
                    List<SensorProperty> properties = new ArrayList<>();
                    properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.SEQUENCE));
                    sensors.add(new Sensor<Number>("Intelligenter Schalter", SensorType.NUMERIC, properties));

                }
                {
                    List<SensorProperty> properties = new ArrayList<>();
                    properties.add(new RangePropertie<Integer>(0, 65000));
                    properties.add(new UnityProperty("^C"));

                    sensors.add(new Sensor<Number>("Temperatur", SensorType.NUMERIC, properties));
                }
                break;
            }

            case SWITCH: {
                List<SensorProperty> properties = new ArrayList<>();
                properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.BOOLEAN));
                sensors.add(new Sensor<Boolean>("Schalter", SensorType.SWITCH, properties));
                break;
            }

            case TEMPERATURE: {
                List<SensorProperty> properties = new ArrayList<>();
                properties.add(new RangePropertie<Integer>(0, 65000));
                properties.add(new UnityProperty("^C"));
                properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.NUMERIC));

                sensors.add(new Sensor<Number>("Temperatur", SensorType.NUMERIC, properties));
                break;
            }

            case BEWEGUNGSENSOR: {
                List<SensorProperty> properties = new ArrayList<>();
                properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.BOOLEAN));

                sensors.add(new Sensor<Number>("Bewegungadetektor", SensorType.SWITCH, properties));
                break;
            }

            case RFID: {
                List<SensorProperty> properties = new ArrayList<>();
                properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.STRING));

                sensors.add(new Sensor<String>("RFID", SensorType.RFID, properties));
                break;
            }

            default:
                return null;
            //throw new SensorConfigurationException("No Sensor-Type found for Serial No:"+serial);
            //throw new SensorConfigurationException("No Sensor-Type found for Serial No:"+serial);
        }
        return sensors;
    }

}
