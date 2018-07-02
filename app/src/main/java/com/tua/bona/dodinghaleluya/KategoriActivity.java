package com.tua.bona.dodinghaleluya;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class KategoriActivity extends AppCompatActivity{
    private ArrayList<Doding> dodingList = new ArrayList<Doding>();
    ListView list;
    private int dodingCount;
    private Doding CurrentDoding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("Kategori Doding"); // set the top title
        DodingDbQuery dbHelper = new DodingDbQuery(this);
        dbHelper.open();
        String sql="select kategori,judul,lirik,count(*) as no from doding group by kategori";
        dodingList = dbHelper.getAllDoding(sql);
        Log.d("datanya",dodingList.toString());
        dodingCount = dodingList.size();
        list = (ListView) findViewById(R.id.listNya);
        KategoriAdapter adapter = new KategoriAdapter(KategoriActivity.this,R.layout.layout_kategori,dodingList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String stringText;

                TextView textview=(TextView)view.findViewById(R.id.textKategori);
                stringText=textview.getText().toString();
                Intent intent = new Intent(KategoriActivity.this,ListKategori.class);
                intent.putExtra("KATEGORI", stringText);
                startActivity(intent);
            }
        });

        dbHelper.close();
    }
}
