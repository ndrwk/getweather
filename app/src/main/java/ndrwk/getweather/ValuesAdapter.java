package ndrwk.getweather;

/**
 * Created by drew on 17.02.16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class ValuesAdapter extends CommonListAdapter {
    ArrayList<Value> mainListArray;

    public ValuesAdapter(Context context, IOnListItemClick callback, int number) {
        super(context, callback);
        mainListArray = ModelUtils.getLastValues();
    }

    @Override
    public CommonListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.value_in_list, parent, false);
        return new ValuesViewHolder(v, viewHolderClickCallback);
    }

    @Override
    public void onBindViewHolder(CommonListViewHolder holder, int position) {
        ValuesViewHolder valuesViewHolder = (ValuesViewHolder) holder;
        valuesViewHolder.position = position;
        valuesViewHolder.valueId.setText("" + mainListArray.get(position).getSensor().getSensorId());
        valuesViewHolder.valueType.setText(mainListArray.get(position).getSensor().getType());
        valuesViewHolder.valueDesc.setText(mainListArray.get(position).getSensor().getDescription());
        valuesViewHolder.value.setText("" + mainListArray.get(position).getValue());
        valuesViewHolder.valuePlace.setText(mainListArray.get(position).getSensor().getPlace());
    }

    @Override
    public int getItemCount() {
        return mainListArray.size();
    }


    public static class ValuesViewHolder extends CommonListViewHolder {

        public TextView valueId;
        public TextView valueType;
        public TextView valueDesc;
        public TextView value;
        public TextView valuePlace;

        public ValuesViewHolder(View v, IOnViewHolderClick listener) {
            super(v, listener);
        }

        @Override
        void findControls(View v) {
            valueId = (TextView) v.findViewById(R.id.valueId);
            valueType = (TextView) v.findViewById(R.id.valueType);
            valueDesc = (TextView) v.findViewById(R.id.valueDesc);
            value = (TextView) v.findViewById(R.id.value);
            valuePlace = (TextView) v.findViewById(R.id.valuePlace);
        }
    }
}