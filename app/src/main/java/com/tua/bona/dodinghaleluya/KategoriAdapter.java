package com.tua.bona.dodinghaleluya;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class KategoriAdapter extends ArrayAdapter<Doding> {
    Activity activity;
    int layoutResourceId;
    ArrayList<Doding> data = new ArrayList<Doding>();
    Doding doding;
    public KategoriAdapter(Activity activity,int layoutResourceId,ArrayList<Doding> data){
        super(activity,layoutResourceId,data);
        this.activity=activity;
        this.layoutResourceId=layoutResourceId;
        this.data= data;
        Log.d("dataBenar",data.toString());
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        BinaanHolder holder=null;


        if(row==null)
        {

            LayoutInflater inflater=LayoutInflater.from(activity);

            row=inflater.inflate(layoutResourceId,parent,false);

            holder=new BinaanHolder();

            holder.kategori= (TextView) row.findViewById(R.id.textKategori);
            holder.counter = (TextView) row.findViewById(R.id.counter);
            row.setTag(holder);

        }
        else
        {
            holder= (BinaanHolder) row.getTag();
        }

        doding=data.get(position);

        holder.kategori.setText(doding.getKategori());
        holder.counter.setText(String.valueOf(doding.getNo()));
        return row;
    }


    class BinaanHolder
    {

        TextView kategori,counter;

    }
}
