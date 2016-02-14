package ndrwk.getweather;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by drew on 14.02.16.
 */
public class AllSensorsFragment extends Fragment implements CommonListAdapter.IOnListItemClick{

    private IListItemClick eventCallback;
    private Activity activity;
    private int fragmentLayout;
    private RecyclerView recyclerView;
    private CommonListAdapter sensorsListAdapter;

    public void update(){
        sensorsListAdapter = new SensorsAdapter(activity, this);
        recyclerView.setAdapter(sensorsListAdapter);
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
//        if (savedInstanceState != null) {
//            fragmentLayout = savedInstanceState.getInt(MainActivity.FragmentFabric.ARG_LAYOUT);
//        }
//        int posInDrawer = getArguments().getInt(MainActivity.FragmentFabric.ARG_POS_IN_DRAWER);
        fragmentLayout = R.layout.fragment_sensors;
        int recyclerContainer = R.id.sensors_list_recycler_view;
        sensorsListAdapter = new SensorsAdapter(activity, this);
        View rootView = inflater.inflate(fragmentLayout, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(recyclerContainer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(sensorsListAdapter);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        eventCallback = (IListItemClick) activity;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putInt(MainActivity.FragmentFabric.ARG_LAYOUT, fragmentLayout);
    }
}