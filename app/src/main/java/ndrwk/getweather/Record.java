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
        this.time = new Date(1000 * time);
        this.values = new ArrayList<>();
        this.values.addAll(values);
    }

    public String getTime() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(time);
    }

    public ArrayList<Value> getValuesArray() {
        return values;
    }

    public String getStrValues(){
        String ret = "";
        for (Value val : values){
//            ret += " " + val.getStrValue();
            ret += " " + 3;
        }
        return ret;
    }
}