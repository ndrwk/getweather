package ndrwk.getweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by drew on 13.02.16.
 */
public class RecordsAdapter extends CommonListAdapter {
    ArrayList<Record> mainListArray;

    public RecordsAdapter(Context context, IOnListItemClick callback) {
        super(context, callback);
        mainListArray = ModelUtils.getRecords();
    }

    @Override
    public CommonListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_in_list_item, parent, false);
        return new RecordsViewHolder(v, viewHolderClickCallback);
    }

    @Override
    public void onBindViewHolder(CommonListViewHolder holder, int position) {
        RecordsViewHolder recordsViewHolder = (RecordsViewHolder) holder;
        recordsViewHolder.position = position;
        recordsViewHolder.time.setText(mainListArray.get(position).getTime());
        recordsViewHolder.values.setText(mainListArray.get(position).getStrValues());
    }

    @Override
    public int getItemCount() {
        return mainListArray.size();
    }


    public static class RecordsViewHolder extends CommonListViewHolder {

        public TextView time;
        public TextView values;

        public RecordsViewHolder(View v, IOnViewHolderClick listener) {
            super(v, listener);
        }

        @Override
        void findControls(View v) {
            time = (TextView) v.findViewById(R.id.recordTime);
            values = (TextView) v.findViewById(R.id.recordValues);
        }


    }
}