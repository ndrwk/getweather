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
import android.widget.Toast;

public class MainListFragment extends Fragment implements CommonListAdapter.IOnListItemClick{

    @Override
    public void onClick(int pos, Record record) {
        eventCallback.mainListClickCallback(pos, record);
    }

    @Override
    public void onLongClick(int pos, Record record){
        Toast.makeText(activity, "LongClick", Toast.LENGTH_SHORT).show();
        eventCallback.mainListLongClickCallback(pos, record);
    }


    public interface IListItemClick {
        void mainListClickCallback(int pos, Record record);
        void mainListLongClickCallback(int pos, Record record);

    }
    private IListItemClick eventCallback;

    Activity activity;
    private int fragmentLayout, recyclerContainer;
    private CommonListAdapter commonListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            fragmentLayout = savedInstanceState.getInt(MainActivity.FragmentFabric.ARG_LAYOUT);
        }
        int posInDrawer = getArguments().getInt(MainActivity.FragmentFabric.ARG_POS_IN_DRAWER);
//        switch (posInDrawer){
//            case 0:
//                fragmentLayout = R.layout.fragment_cards;
//                recyclerContainer = R.id.cardslist_recycler_view;
//                commonListAdapter = new CardsAdapter(activity, this);
//                break;
//            case 1:
//                fragmentLayout = R.layout.fragment_commandslist;
//                recyclerContainer = R.id.commandslist_recycler_view;
//                commonListAdapter = new AllCommandsListAdapter(activity, this);
//        }
        fragmentLayout = R.layout.fragment_records;
        recyclerContainer = R.id.commandslist_recycler_view;
        commonListAdapter = new RecordsAdapter(activity, this);
        View rootView = inflater.inflate(fragmentLayout, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(recyclerContainer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(commonListAdapter);
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
        outState.putInt(MainActivity.FragmentFabric.ARG_LAYOUT, fragmentLayout);
    }
}