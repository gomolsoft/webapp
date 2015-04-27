package de.moso.entity.typify;

/**
 * Created by sandro on 26.04.15.
 */
public enum SerialNoTypifier {
    //Sensor
    DISTANCE("301"), BEWEGUNGSENSOR("304"), TEMPERATURE("302"), HUMIDITY("303"), SWITCH("101"), INTELIGENTLIGHTSWITCH("202")

    //Aktor
    , HEIZUNGREGLER("503"), STECKDOSE("501")

    //Unbekannt
    , UNIDENTIFIED("XXX");

    private String serialNoEnd;

    SerialNoTypifier(String serialNoEnd) {
        this.serialNoEnd = serialNoEnd;
    }

    public static SerialNoTypifier findBySerialNo(final String serialNo) {
        for (SerialNoTypifier serialNoType : SerialNoTypifier.values()) {
            if (serialNo.endsWith(serialNoType.serialNoEnd))
                return serialNoType;
        }
        return UNIDENTIFIED;
    }

    public String getSerialNoEnd() {
        return serialNoEnd;
    }
}
