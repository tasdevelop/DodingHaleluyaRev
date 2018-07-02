package com.tua.bona.dodinghaleluya;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListKategori extends AppCompatActivity {
    String kategori;
    private ArrayList<Doding> dodingList = new ArrayList<Doding>();
    ListView list;
    private int dodingCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kategori);
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("List berdasarkan kategori"); // set the top title
//        actionBar.setHomeButtonEnabled(true);
        kategori = getIntent().getStringExtra("KATEGORI");
        DodingDbQuery dbHelper = new DodingDbQuery(this);
        dbHelper.open();
        String sql="select * from doding where kategori ='"+ kategori+"'";
        TextView txtKategori = findViewById(R.id.textKategori);
        txtKategori.setText(kategori);
        dodingList = dbHelper.getAllDoding(sql);
        dodingCount = dodingList.size();
        list = (ListView) findViewById(R.id.listDoding);
        ListKategoriAdapter adapter = new ListKategoriAdapter(ListKategori.this,R.layout.layout_list_kategori,dodingList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String stringText;

                TextView textview=(TextView)view.findViewById(R.id.textId);
                stringText=textview.getText().toString();
                Intent intent = new Intent(ListKategori.this,DodingActivity.class);
                intent.putExtra("NO", stringText);
                startActivity(intent);
            }
        });
    }
}
