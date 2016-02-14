package ndrwk.getweather;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IAsyncResponse, IListItemClick {

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
            JSONTask jsonTask = new JSONTask();
            jsonTask.result = MainActivity.this;
            switch (id) {
                case R.id.weather_sensors:
                    jsonTask.execute(API.getLast());
                    fragment = new AllSensorsFragment();
                    break;
                case R.id.weather_all:
                    jsonTask.result = MainActivity.this;
                    jsonTask.execute(API.getAll());
                    fragment = new AllRecordsFragment();
                    break;
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        return true;
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
                if (fragment instanceof AllRecordsFragment)
                    ((AllRecordsFragment)fragment).update();
                if (fragment instanceof AllSensorsFragment)
                    ((AllSensorsFragment)fragment).update();
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
        Snackbar.make(MainActivity.this.getWindow().getDecorView().getRootView(), "click from MainList " + pos, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void mainListLongClickCallback(int pos) {
        Snackbar.make(MainActivity.this.getWindow().getDecorView().getRootView(), "longclick from MainList " + pos, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
