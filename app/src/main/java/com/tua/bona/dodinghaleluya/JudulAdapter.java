package com.tua.bona.dodinghaleluya;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class JudulAdapter extends ArrayAdapter<Doding> {
    Activity activity;
    int layoutResourceId;
    ArrayList<Doding> data = new ArrayList<Doding>();
    Doding doding;
    public JudulAdapter(Activity activity,int layoutResourceId,ArrayList<Doding> data){
        super(activity,layoutResourceId,data);
        this.activity=activity;
        this.layoutResourceId=layoutResourceId;
        this.data= data;
        Log.d("dataBenar",data.toString());
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        DodingKategoriHolder holder=null;


        if(row==null)
        {

            LayoutInflater inflater=LayoutInflater.from(activity);

            row=inflater.inflate(layoutResourceId,parent,false);

            holder=new DodingKategoriHolder();

            holder.judul= (TextView) row.findViewById(R.id.textJudul);
            holder.id = (TextView) row.findViewById(R.id.textId);
            row.setTag(holder);

        }
        else
        {
            holder= (DodingKategoriHolder) row.getTag();
        }

        doding=data.get(position);

        holder.judul.setText(String.valueOf(doding.getNo()) +". " +doding.getJudul());
        holder.id.setText(String.valueOf(doding.getNo()));
        return row;
    }


    class DodingKategoriHolder
    {

        TextView judul,id;

    }
}
