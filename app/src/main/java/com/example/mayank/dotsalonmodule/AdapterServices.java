package com.example.mayank.dotsalonmodule;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mayank on 25/06/16.
 */public class AdapterServices extends RecyclerView.Adapter<AdapterServices.ElementHolder>{

    android.content.Context context;


    public AdapterServices() {

    }

    public static class ElementHolder extends RecyclerView.ViewHolder {

        CardView cv;

        public ElementHolder(View v) {
            super(v);
            cv = (CardView) v.findViewById(R.id.itemcard);

        }
    }

    public AdapterServices.ElementHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_service, parent, false);
        ElementHolder eh = new ElementHolder(v);
        return eh;

    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ElementHolder h, final int position) {



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 7;
    }

}


