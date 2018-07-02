package com.tua.bona.dodinghaleluya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import java.util.ArrayList;

public class DodingActivity extends AppCompatActivity {
    private ArrayList<Doding> dodingList = new ArrayList<Doding>();
    WebView webDoding;
    String lirik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doding);
        String idDoding = getIntent().getStringExtra("NO");
        DodingDbQuery dbHelper = new DodingDbQuery(this);
        dbHelper.open();
        String sql="select * from doding where no ='"+ idDoding+"'";
        dodingList = dbHelper.getAllDoding(sql);
        lirik="";
        lirik += "<b>"+String.valueOf(dodingList.get(0).getNo())+". "+dodingList.get(0).getJudul()+"</b><br>";
        lirik += "<i>("+dodingList.get(0).getKategori()+")</i><br><br>";
        lirik +=dodingList.get(0).getLirik();
        webDoding = (WebView)findViewById(R.id.webDoding);
        webDoding.loadData(lirik, "text/html; charset=utf-8", "utf-8");
    }
}
