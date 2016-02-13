package ndrwk.getweather;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AsyncResponse, MainListFragment.IListItemClick {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONTask jsonTask = new JSONTask();
                jsonTask.result = MainActivity.this;
                jsonTask.execute(API.getAll());

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.weather_last:
                Toast.makeText(this, "get last", Toast.LENGTH_LONG).show();
                fragment = FragmentFabric.newInstance(0);
                break;
            case R.id.weather_all:
                Toast.makeText(this, "get all", Toast.LENGTH_LONG).show();
                fragment = FragmentFabric.newInstance(1);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        return true;
    }

    @Override
    public void asyncResponse(String json) {
        ModelUtils.retrieveSensors(json);
        ModelUtils.retrieveRecords(json);
        Toast.makeText(this, json, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        JSONTask jsonTask = new JSONTask();
        jsonTask.result = MainActivity.this;
        jsonTask.execute(API.getAll());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void mainListClickCallback(int pos, Record record) {
        Toast.makeText(this, "click from MainList", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mainListLongClickCallback(int pos, Record record) {
        Toast.makeText(this, "longclick from MainList", Toast.LENGTH_SHORT).show();
    }


    public static class FragmentFabric extends Fragment {
        public static final String ARG_LAYOUT = "layout";
        public static final String ARG_POS_IN_DRAWER = "posindrawer";
        public static final String ARG_CATEGORY = "category";
        private static Fragment fragmentInstance;
        private static DialogFragment dialogFragmentInstance;
//        private static ButtonsFragment btnFragm;

        public static Fragment newInstance(int selectedDrawerPosition) {
            switch (selectedDrawerPosition) {
                case 0:
                    break;
                case 1:
                    fragmentInstance = new MainListFragment();
                    Bundle args = new Bundle();
                    args.putInt(ARG_POS_IN_DRAWER, selectedDrawerPosition);
                    fragmentInstance.setArguments(args);
                    break;
            }
            return fragmentInstance;
        }

//        public static DialogFragment newDialogInstance(Categories category) {
//            dialogFragmentInstance = new FloatingList();
//            Bundle args = new Bundle();
//            args.putSerializable(ARG_CATEGORY, category);
//            dialogFragmentInstance.setArguments(args);
//            return dialogFragmentInstance;
//        }
    }


}
