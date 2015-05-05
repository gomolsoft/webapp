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
                List<IoTProperty> properties = new ArrayList<>();
                properties.add(new RangePropertie<Integer>(0, 255));
                properties.add(new UnityProperty("cm"));
                properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.NUMERIC));

                sensors.add(new Sensor<Number>("Distanz", properties));
                break;
            }

            case HUMIDITY: {
                {
                    List<IoTProperty> properties = new ArrayList<>();
                    properties.add(new RangePropertie<Integer>(0, 255));
                    properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.NUMERIC));

                    sensors.add(new Sensor<Number>("Feuchtigkeit", properties));
                }

                {
                    List<IoTProperty> properties = new ArrayList<>();
                    properties.add(new RangePropertie<Integer>(0, 65000));
                    properties.add(new UnityProperty("^C"));
                    properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.NUMERIC));

                    sensors.add(new Sensor<Number>("Temperatur", properties));
                }
                break;
            }

            case INTELIGENTLIGHTSWITCH: {
                {
                    List<IoTProperty> properties = new ArrayList<>();
                    properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.SEQUENCE));
                    sensors.add(new Sensor<Number>("Intelligenter Schalter", properties));

                }
                {
                    List<IoTProperty> properties = new ArrayList<>();
                    properties.add(new RangePropertie<Integer>(0, 65000));
                    properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.NUMERIC));
                    properties.add(new UnityProperty("^C"));

                    sensors.add(new Sensor<Number>("Temperatur", properties));
                }
                break;
            }

            case SWITCH: {
                List<IoTProperty> properties = new ArrayList<>();
                properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.BOOLEAN));
                sensors.add(new Sensor<Boolean>("Schalter", properties));
                break;
            }

            case TEMPERATURE: {
                List<IoTProperty> properties = new ArrayList<>();
                properties.add(new RangePropertie<Integer>(0, 65000));
                properties.add(new UnityProperty("^C"));
                properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.NUMERIC));

                sensors.add(new Sensor<Number>("Temperatur", properties));
                break;
            }

            case BEWEGUNGSENSOR: {
                List<IoTProperty> properties = new ArrayList<>();
                properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.BOOLEAN));

                sensors.add(new Sensor<Number>("Bewegungadetektor", properties));
                break;
            }

            case RFID: {
                List<IoTProperty> properties = new ArrayList<>();
                properties.add(new ValueTypeProperty(ValueTypeProperty.ValueTypePropertyType.STRING));

                sensors.add(new Sensor<String>("STRING", properties));
                break;
            }

            default:
                return null;
        }
        return sensors;
    }

}
