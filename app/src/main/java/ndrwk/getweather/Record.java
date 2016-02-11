package ndrwk.getweather;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by drew on 09.02.16.
 */
public class Record {
    private Date time;
    private ArrayList<Value> values;

    public Record(long time, ArrayList<Value> values) {
        this.time = new Date(time);
        this.values = new ArrayList<>();
        this.values.addAll(values);
    }

    public Date getTime() {
        return time;
    }

    public ArrayList<Value> getValuesArray() {
        return values;
    }
}