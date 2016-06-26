package com.example.mayank.dotsalonmodule;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mayank on 26/06/16.
 */
public class AdapterCompletedServices extends RecyclerView.Adapter<AdapterCompletedServices.ElementHolder>{

    android.content.Context context;

    ArrayList<Data> Dataset;

    public AdapterCompletedServices(ArrayList<Data> d) {
        Dataset = d;
    }

    public static class ElementHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView[] tv = new TextView[3];
        EditText et;
        LinearLayout ll;

        public ElementHolder(View v) {
            super(v);

            cv = (CardView) v.findViewById(R.id.itemcard);
            tv[0] = (TextView) v.findViewById(R.id.text1);
            tv[1] = (TextView) v.findViewById(R.id.text2);
            tv[2] = (TextView) v.findViewById(R.id.text3);

        }
    }

    public AdapterCompletedServices.ElementHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        View v;

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item2, parent, false);

        ElementHolder eh = new ElementHolder(v);
        return eh;

    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ElementHolder h, final int position) {

        final Data temp = Dataset.get(position);

        h.tv[0].setText(temp.name);

        h.tv[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context,CustomerDetail.class);
                intent.putExtra("Data",temp);
                intent.putExtra("compl",true);
                context.startActivity(intent);
            }
        });

        h.tv[1].setText(temp.getDate() + temp.slot);

        h.tv[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context,CustomerDetail.class);
                intent.putExtra("Data",temp);
                intent.putExtra("compl",true);
                context.startActivity(intent);
            }
        });

        h.tv[2].setText("Rs. "+temp.price);

        h.tv[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context,CustomerDetail.class);
                intent.putExtra("Data",temp);
                intent.putExtra("compl",true);
                context.startActivity(intent);
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return Dataset.size();
    }

}


