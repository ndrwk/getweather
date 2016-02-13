package ndrwk.getweather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    public String getTime() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(time);
    }

    public ArrayList<Value> getValuesArray() {
        return values;
    }

    public String getValues(){
        return values.get(0).getValue() + " " + values.get(1).getValue() + " " +values.get(2).getValue();
    }
}