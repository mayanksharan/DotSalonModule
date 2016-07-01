package com.example.mayank.dotsalonmodule;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.MetricAffectingSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by mayank on 25/06/16.
 */
public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ElementHolder>{

    android.content.Context context;

    ArrayList<Data> Dataset;
    boolean completed;

    public AdapterItems(ArrayList<Data> d,boolean b) {
        Dataset = d;
        completed = b;
    }

    public static class ElementHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView[] tv = new TextView[3];
        EditText et;
        LinearLayout ll;

        public ElementHolder(View v,boolean comp) {
            super(v);

            cv = (CardView) v.findViewById(R.id.itemcard);
            tv[0] = (TextView) v.findViewById(R.id.text1);
            tv[1] = (TextView) v.findViewById(R.id.text2);
            tv[2] = (TextView) v.findViewById(R.id.text3);

            if(!comp) {

                et = (EditText) v.findViewById(R.id.pin);
                ll = (LinearLayout) v.findViewById(R.id.pin_back);
            }
        }
    }

    public AdapterItems.ElementHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        View v;

        if(completed)
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item2, parent, false);
        else
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);

        ElementHolder eh = new ElementHolder(v,completed);
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
                intent.putExtra("compl",completed);
                context.startActivity(intent);
            }
        });

        h.tv[1].setText(temp.getDate() + temp.slot);

        h.tv[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context,CustomerDetail.class);
                intent.putExtra("Data",temp);
                intent.putExtra("compl",completed);
                context.startActivity(intent);
            }
        });

        h.tv[2].setText("Rs. "+temp.price);

        h.tv[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context,CustomerDetail.class);
                intent.putExtra("Data",temp);
                intent.putExtra("compl",completed);
                context.startActivity(intent);
            }
        });

        if(!completed) {
            h.et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length() == 5) {
                        temp.tr++;
                        if(temp.tr < 3) {
                            if (temp.pin * 10 == Integer.parseInt(s + "0")) {
                                Log.e("VALUE SET", temp.name + " true");
                                PlaceholderFragment.snackbarError("PIN Correctly Entered");
                                h.ll.setBackgroundColor(Color.parseColor("#6600ff00"));
                                temp.completed = true;
                            }
                            else
                            {
                                Log.e("VALUE SET", temp.name + " false");
                                PlaceholderFragment.snackbarError("Incorrect PIN");
                            }
                        }
                        else
                        {
                            Log.e("VALUE SET", temp.name + " false");
                            PlaceholderFragment.snackbarError("PIN Incorrectly Entered Thrice");
                            h.ll.setBackgroundColor(Color.parseColor("#66ff0000"));
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
            return Dataset.size();
    }

}

