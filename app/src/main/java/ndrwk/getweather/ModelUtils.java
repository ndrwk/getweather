package ndrwk.getweather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by drew on 11.02.16.
 */
public class ModelUtils {

    private static final String ID = "id";
    private static final String TYPE = "type";
    private static final String SERNUM = "sernum";
    private static final String DESCRIPTION = "description";
    private static final String PLACE = "place";
    private static final String SENSORS = "sensors";
    private static final String RECORDS = "records";
    private static final String TIME = "time";
    private static ArrayList<Sensor> sensors;
    private static ArrayList<Record> records;


    public static ArrayList<Sensor> getSensors(String json) {
        if (sensors == null) {
            sensors = new ArrayList<>();
        } else {
            sensors.clear();
        }
        JSONObject dataJsonObj;
        try {
            dataJsonObj = new JSONObject(json);
            JSONArray allSensors = dataJsonObj.getJSONArray(SENSORS);
            for (int i = 0; i < allSensors.length(); i++) {
                JSONObject jsonSensor = allSensors.getJSONObject(i);
                int id = jsonSensor.getInt(ID);
                String type = jsonSensor.getString(TYPE);
                String sernum = jsonSensor.getString(SERNUM);
                String description = jsonSensor.getString(DESCRIPTION);
                String place = jsonSensor.getString(PLACE);
                Sensor sensorItem = new Sensor(id, type, sernum, description, place);
                sensors.add(sensorItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sensors;
    }

    public static ArrayList<Record> getRecords(String json) {
        if (records == null) {
            records = new ArrayList<>();
        } else {
            records.clear();
        }
        JSONObject dataJsonObj;
        try {
            int numbers = sensors.size();
            dataJsonObj = new JSONObject(json);
            JSONArray allRecords = dataJsonObj.getJSONArray(RECORDS);
            for (int i = 0; i < allRecords.length(); i++){
                JSONObject jsonRecord = allRecords.getJSONObject(i);
                long time = jsonRecord.getLong(TIME);
                ArrayList<Value> values = new ArrayList<>();
                for (int j = 1; j < numbers+1; j++){
                    double value = jsonRecord.getDouble("" + j);
                    values.add(new Value(sensors.get(j-1), value));
                }
                records.add(new Record(time, values));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return records;
    }

}
