package com.tua.bona.dodinghaleluya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import com.tua.bona.dodinghaleluya.DodingDbQuery;

public class MainActivity extends AppCompatActivity  implements  View.OnClickListener {
    private CardView noCard,judulCard,katCard,aboutCard;
    private ArrayList<Doding> dodingList;
    private int dodingCount;
    private Doding CurrentDoding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Prefs.getApplicationVersionCode(this) < AppVersionCode.getApkVersionCode(this))
            checkApplicationVersionCode();
        DodingDbQuery dbHelper = new DodingDbQuery(this);
        dbHelper.open();

        dbHelper.close();
        noCard = (CardView)findViewById(R.id.noCard);
        katCard = (CardView)findViewById(R.id.katCard);
        judulCard = (CardView) findViewById(R.id.judulCard);
        aboutCard = (CardView) findViewById(R.id.aboutCard);
        noCard.setOnClickListener(this);
        katCard.setOnClickListener(this);
        judulCard.setOnClickListener(this);
        aboutCard.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        Intent i;
        switch (v.getId()){
            case R.id.noCard : i = new Intent(this,NoActivity.class);startActivity(i);break;
            case R.id.katCard : i = new Intent(this,KategoriActivity.class);startActivity(i);break;
            case R.id.judulCard : i = new Intent(this,JudulActivity.class);startActivity(i);break;
            case R.id.aboutCard : i = new Intent(this,AboutActivity.class);startActivity(i);break;
            default:break;
        }
    }
    void checkApplicationVersionCode() {
        switch(Prefs.getApplicationVersionCode(this)) {
            /**********   for the very first time only   **********/
            /**********   things that we need only once  **********/
            case PDefaultValue.VERSION_CODE:
                DodingDbQuery db = new DodingDbQuery(this);
                db.createDatabase();
                db.close();
                //put this apk version code in Shared Preference
                Prefs.putApplicationVersionCode(this, AppVersionCode.getApkVersionCode(this));
                //recall this method so that doWorkOnLatestVersion() can be called
                checkApplicationVersionCode();
                break;
            //**********************************************************//
            default:
                //do this for every version
                break;
        }
    }

}
