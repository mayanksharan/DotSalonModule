package com.example.mayank.dotsalonmodule;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CustomerDetail extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context mContext = this;
    Data temp;
    boolean completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.service_recycler_view);
        final RecyclerView.Adapter[] mAdapter = new RecyclerView.Adapter[1];

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter[0] = new AdapterServices();
        mRecyclerView.setAdapter(mAdapter[0]);

        temp = (Data) getIntent().getSerializableExtra("Data");

        final LinearLayout ll =(LinearLayout) findViewById(R.id.pin_back2);
        TextView t = (TextView) findViewById(R.id.done);

        completed = getIntent().getBooleanExtra("compl",false);
        if(completed) {
            ll.setVisibility(View.INVISIBLE);
            t.setVisibility(View.VISIBLE);
        }

        EditText et = (EditText) findViewById(R.id.pin2);


        if(temp.completed)
        {
            et.setText(temp.pin+"");
            ll.setBackgroundColor(Color.parseColor("#6600ff00"));
        }

        if(et!=null) {
            et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (temp.pin * 10 == Integer.parseInt(s + "0")) {
                        Log.e("VALUE SET", temp.name + "true");
                        snackbarError("PIN Correctly Entered");
                        ll.setBackgroundColor(Color.parseColor("#6600ff00"));
                        temp.completed = true;
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }

    public void snackbarError(String msg)
    {
        Snackbar.make(findViewById(R.id.ll), msg, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED)
                .show();

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
