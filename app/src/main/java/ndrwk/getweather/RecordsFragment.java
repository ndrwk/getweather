package ndrwk.getweather;

/**
 * Created by drew on 13.02.16.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecordsFragment extends Fragment implements CommonListAdapter.IOnListItemClick{

    private IListItemClick eventCallback;
    private Activity activity;
    private RecyclerView recyclerView;
    private CommonListAdapter recordsListAdapter;

    public void update(){
        recordsListAdapter = new RecordsAdapter(activity, this);
        recyclerView.setAdapter(recordsListAdapter);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int recyclerContainer = R.id.records_list_recycler_view;
        recordsListAdapter = new RecordsAdapter(activity, this);
        View rootView = inflater.inflate(R.layout.fragment_records, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(recyclerContainer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(recordsListAdapter);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        eventCallback = (IListItemClick) activity;
    }

}