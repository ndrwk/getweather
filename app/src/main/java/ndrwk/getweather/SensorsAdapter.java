package ndrwk.getweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by drew on 14.02.16.
 */
public class SensorsAdapter extends CommonListAdapter {
    ArrayList<Sensor> mainListArray;

    public SensorsAdapter(Context context, IOnListItemClick callback) {
        super(context, callback);
        mainListArray = ModelUtils.getSensors();
    }

    @Override
    public CommonListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sensor_in_list_item, parent, false);
        return new SensorsViewHolder(v, viewHolderClickCallback);
    }

    @Override
    public void onBindViewHolder(CommonListViewHolder holder, int position) {
        SensorsViewHolder sensorsViewHolder = (SensorsViewHolder) holder;
        sensorsViewHolder.position = position;
        sensorsViewHolder.sensorId.setText(mainListArray.get(position).getSensorId() + "");
        sensorsViewHolder.sensorType.setText(mainListArray.get(position).getType());
        sensorsViewHolder.sensorSernum.setText(mainListArray.get(position).getSernum());
        sensorsViewHolder.sensorDescr.setText(mainListArray.get(position).getDescription());
        sensorsViewHolder.sensorPlace.setText(mainListArray.get(position).getPlace());
    }

    @Override
    public int getItemCount() {
        return mainListArray.size();
    }


    public static class SensorsViewHolder extends CommonListViewHolder {

        public TextView sensorId;
        public TextView sensorType;
        public TextView sensorSernum;
        public TextView sensorDescr;
        public TextView sensorPlace;

        public SensorsViewHolder(View v, IOnViewHolderClick listener) {
            super(v, listener);
        }

        @Override
        void findControls(View v) {
            sensorId = (TextView) v.findViewById(R.id.sensorId);
            sensorType = (TextView) v.findViewById(R.id.sensorType);
            sensorSernum = (TextView) v.findViewById(R.id.sensorSernum);
            sensorDescr = (TextView) v.findViewById(R.id.sensorDescr);
            sensorPlace = (TextView) v.findViewById(R.id.sensorPlace);
        }


    }
}