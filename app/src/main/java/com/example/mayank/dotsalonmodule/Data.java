package com.example.mayank.dotsalonmodule;


import java.io.Serializable;

/**
 * Created by mayank on 25/06/16.
 */
public class Data implements Serializable{

    String slot;
    String name;
    int pin, price, id;
    long date;
    boolean completed;
    int tr;

    Data(int i, String s, String n, int p, int pr, boolean c,long d)
    {
        slot = s;
        name = n;
        pin = p;
        price = pr;
        completed = c;
        date = d;
        id = i;
        tr = 0;
    }

    public String getDate()
    {
        return date%100 + "-" + (date/100)%100 + "-" + date/10000 +" ";
    }
}
