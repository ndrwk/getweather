package ndrwk.getweather;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IAsyncResponse, IListItemClick {

    public static final int SENSORS = 0;
    public static final int ALL_RECORDS = 1;
    public static final int LAST_RECORD = 2;
    public static final String NUMBER_IN_LIST = "number";
    private Fragment fragment;
    private int storedMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setFragment(LAST_RECORD);
    }

    @Override
    public void onBackPressed() {
        fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id != storedMenuItem) {
            storedMenuItem = id;
            switch (id) {
                case R.id.weather_sensors:
                    setFragment(SENSORS);
                    break;
                case R.id.weather_all:
                    setFragment(ALL_RECORDS);
                    break;
                case R.id.weather_last:
                    setFragment(LAST_RECORD);
                    break;
            }
        }
        return true;
    }

    private void setFragment(int num){
        JSONTask jsonTask = new JSONTask();
        jsonTask.result = MainActivity.this;
        switch (num){
            case SENSORS:
                jsonTask.execute(API.getLast());
                fragment = new SensorsFragment();
                break;
            case ALL_RECORDS:
                jsonTask.result = MainActivity.this;
//                jsonTask.execute(API.getInterval(1455551638, 1455551638 + 86400 * 31));
                jsonTask.execute(API.getInterval(1456330494, 1456330494 + 86400 / 24));
                fragment = new RecordsFragment();
                break;
            case LAST_RECORD:
                jsonTask.execute(API.getLast());
                fragment = new ValuesFragment();
                Bundle args = new Bundle();
                args.putInt(NUMBER_IN_LIST, 0);
                fragment.setArguments(args);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public void asyncResponse(String json) {
        switch (json){
            case JSONTask.IOERROR:
                Snackbar.make(MainActivity.this.getWindow().getDecorView().getRootView(), "IO Error", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
                break;
            case JSONTask.TIMEOUTERROR:
                Snackbar.make(MainActivity.this.getWindow().getDecorView().getRootView(), "Timeout Error", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
                break;
            case JSONTask.URLERROR:
                Snackbar.make(MainActivity.this.getWindow().getDecorView().getRootView(), "URL Error", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
                break;
            default:
                ModelUtils.retrieveSensors(json);
                ModelUtils.retrieveRecords(json);
                if (fragment instanceof RecordsFragment)
                    ((RecordsFragment)fragment).update();
                if (fragment instanceof SensorsFragment)
                    ((SensorsFragment)fragment).update();
                if (fragment instanceof ValuesFragment)
                    ((ValuesFragment)fragment).update();
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//    }

    @Override
    public void mainListClickCallback(int pos) {
        String backStateName =  fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            fragment = new ValuesFragment();
            Bundle args = new Bundle();
            args.putInt(NUMBER_IN_LIST, pos);
            fragment.setArguments(args);
            ft.replace(R.id.container, fragment, backStateName);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
//        if (fragment instanceof RecordsFragment){
//            fragment = new ValuesFragment();
//            Bundle args = new Bundle();
//            args.putInt(NUMBER_IN_LIST, pos);
//            fragment.setArguments(args);
//            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, fragment).commit();
//        }
    }

    @Override
    public void mainListLongClickCallback(int pos) {
        Snackbar.make(MainActivity.this.getWindow().getDecorView().getRootView(), "longclick from MainList " + pos, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
