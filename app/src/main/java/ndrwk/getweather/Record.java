package ndrwk.getweather;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by drew on 09.02.16.
 */
public class Record implements Comparable{
    private Date time;
    private ArrayList<Value> values;
    private long xTime;

    public Record(long time, ArrayList<Value> values) {
        xTime = time;
        this.time = new Date(1000 * time);
        this.values = new ArrayList<>();
        this.values.addAll(values);
    }

    public String getTime() {
        return ModelUtils.getDf().format(time);
    }

    public ArrayList<Value> getValuesArray() {
        return values;
    }

    public String getStrValues(){
        String ret = "";
        for (Value val : values){
            ret += val.getStrValue() + "\n";
        }
        return ret;
    }

    public long getxTime() {
        return xTime;
    }

    @Override
    public int compareTo(@NonNull Object another) {
        return (int)(this.xTime - ((Record)another).getxTime());
    }
}