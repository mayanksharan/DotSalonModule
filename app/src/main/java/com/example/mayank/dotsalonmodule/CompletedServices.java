package com.example.mayank.dotsalonmodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class CompletedServices extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Data> mDataset,Dataset;
    Context mContext = this;
    int indate = 20160801 ,enddate = 20160831;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_services);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        indate = intent.getIntExtra("indate", 00000000);
        enddate = intent.getIntExtra("enddate",99999999);

        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.comp_recycler_view);
        final RecyclerView.Adapter[] mAdapter = new RecyclerView.Adapter[1];

        setDataset();
        filterDataset();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter[0] = new AdapterCompletedServices(Dataset);
        mRecyclerView.setAdapter(mAdapter[0]);

    }

    private void setDataset()
    {
        mDataset = new ArrayList<>();
        mDataset.add(new Data(1, "18:00 - 19:00","GUY 1",12345,200,true,20160801));
        mDataset.add(new Data(2, "18:30 - 19:30","GUY 2",12345,100,true,20160804));
        mDataset.add(new Data(3, "22:00 - 23:59","GUY 3",12345,150,false,20160809));
        mDataset.add(new Data(4, "14:00 - 15:00","GUY 4",12345,170,true,20160811));
        mDataset.add(new Data(5, "13:00 - 14:00","GUY 5",12345,90,false,20160823));
        mDataset.add(new Data(6, "13:00 - 14:00","GUY 6",12345,120,true,20160826));
        mDataset.add(new Data(7, "18:00 - 19:00","GUY 7",12345,200,true,20160825));
        mDataset.add(new Data(8, "18:30 - 19:30","GUY 8",12345,100,true,20160826));
        mDataset.add(new Data(9, "22:00 - 23:59","GUY 9",12345,150,false,20160821));
        mDataset.add(new Data(10, "14:00 - 15:00","GUY 10",12345,170,true,20160902));
        mDataset.add(new Data(11, "13:00 - 14:00","GUY 11",12345,90,false,20160904));
        mDataset.add(new Data(12, "13:00 - 14:00","GUY 12",12345,120,true,20160907));
    }

    private void filterDataset()
    {
        Dataset = new ArrayList<>();
        for(int i = 0; i < mDataset.size();i++)
        {
            Data temp = mDataset.get(i);

            if(temp.completed && temp.date <= enddate && temp.date >= indate)
            {
                Dataset.add(temp);
            }
        }
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
        getMenuInflater().inflate(R.menu.completed_services, menu);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
