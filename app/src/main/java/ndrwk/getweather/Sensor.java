package ndrwk.getweather;

import android.support.annotation.NonNull;

/**
 * Created by drew on 09.02.16.
 */
public class Sensor implements Comparable{
    private int id;
    private String type;
    private String sernum;
    private String description;
    private String place;

    public Sensor(int id, String type, String sernum, String description, String place) {
        this.id = id;
        this.type = type;
        this.sernum = sernum;
        this.description = description;
        this.place = place;
    }

    public int getSensorId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSernum() {
        return sernum;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public int compareTo(@NonNull Object another) {
        return this.id - ((Sensor)another).getSensorId();
    }
}
