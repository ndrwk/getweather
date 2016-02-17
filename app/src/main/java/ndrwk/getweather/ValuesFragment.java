package ndrwk.getweather;

/**
 * Created by drew on 17.02.16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ValuesFragment extends Fragment implements CommonListAdapter.IOnListItemClick{

    private IListItemClick eventCallback;
    private Activity activity;
    private RecyclerView recyclerView;
    private CommonListAdapter valuesListAdapter;
    private int number;

    public void update(){
        valuesListAdapter = new ValuesAdapter(activity, this, number);
        recyclerView.setAdapter(valuesListAdapter);
        recyclerView.invalidate();
    }

    @Override
    public void onClick(int pos) {
        eventCallback.mainListClickCallback(pos);
    }

    @Override
    public void onLongClick(int pos){
        eventCallback.mainListLongClickCallback(pos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int number = getArguments().getInt(MainActivity.NUMBER_IN_LIST);
        valuesListAdapter = new ValuesAdapter(activity, this, number);
        View rootView = inflater.inflate(R.layout.fragment_values, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.values_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(valuesListAdapter);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        eventCallback = (IListItemClick) activity;
    }

}