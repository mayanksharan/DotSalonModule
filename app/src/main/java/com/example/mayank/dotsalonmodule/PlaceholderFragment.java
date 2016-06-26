package com.example.mayank.dotsalonmodule;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Created by mayank on 24/06/16.
 */

public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static Context mContext;
    private static final String ARG_SECTION_NUMBER = "section_number";
    static int secNum;
    ArrayList<Data> Dataset;
    static View rootView;
    final RecyclerView.Adapter[] mAdapter = new RecyclerView.Adapter[1];

    public PlaceholderFragment() {
        mContext = getContext();
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        fragment.createDataset(sectionNumber);
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        secNum = sectionNumber;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.item_recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter[0]);

        return rootView;
    }

    public static void snackbarError(String msg)
    {
        Snackbar.make(rootView.findViewById(R.id.rel_frag), msg, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED)
                .show();

    }

    public void createDataset(int sn)
    {
        secNum = sn;
        Dataset = new ArrayList<>();
        ArrayList<Data> gData = MainActivity.getDataset();
        Iterator<Data> iter = gData.iterator();

        GregorianCalendar gCalendar = new GregorianCalendar();
        long date = gCalendar.get(Calendar.YEAR) * 10000 + (gCalendar.get(Calendar.MONTH)+1) * 100 + gCalendar.get(Calendar.DATE);
        int time = gCalendar.get(Calendar.HOUR_OF_DAY) * 100 + gCalendar.get(Calendar.MINUTE);
        Log.e("se ",secNum+"");
        int ti,tf;
        String tis,tfs;

        while(iter.hasNext())
        {
            Data temp = iter.next();
            int i = 0;
            tis = "";

            while(temp.slot.charAt(i)!= ' ')
            {
                if(temp.slot.charAt(i)!= ':')
                {
                    tis += temp.slot.charAt(i);
                }
                i++;
            }

            i+= 3;
            tfs = "";

            while(i < temp.slot.length())
            {
                if(temp.slot.charAt(i)!= ':')
                {
                    tfs += temp.slot.charAt(i);
                }
                i++;
            }

            ti = Integer.parseInt(tis);
            tf = Integer.parseInt(tfs);

            Log.e("DATAPOINT", tis + " " + tfs + " " + time + " " + date + " " + temp.date + " " + temp.name + " " + secNum);

            if(secNum == 0)
            {
                if(date < temp.date || (date == temp.date && time < ti))
                {
                    Dataset.add(temp);
                }
            }

            if(secNum == 1)
            {

                if(date == temp.date && time > ti && time < tf)
                {
                    Dataset.add(temp);
                }
            }

            if(secNum == 2)
            {

                if((date > temp.date || (date == temp.date && time > tf)) && !temp.completed )
                {
                    Dataset.add(temp);
                }
            }

            if(secNum == 3)
            {
                if((date > temp.date || (date == temp.date && time > tf)) && temp.completed )
                {
                    Dataset.add(temp);
                }
            }
        }
        if(secNum == 3)
            mAdapter[0] = new AdapterItems(Dataset,true);
        else
            mAdapter[0] = new AdapterItems(Dataset,false);
    }

}