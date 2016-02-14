package ndrwk.getweather;

/**
 * Created by drew on 10.02.16.
 */
public class Value {
    Sensor sensor;
    double value;

    public Value(Sensor sensor, double value) {
        this.sensor = sensor;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getStrValue(){
        return String.format("%4.1f", value);
    }

    public Sensor getSensor() {
        return sensor;
    }
}

