package com.tua.bona.dodinghaleluya;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class JudulActivity extends AppCompatActivity {
    private ArrayList<Doding> dodingList = new ArrayList<Doding>();
    ListView list;
    private int dodingCount;
    private Doding CurrentDoding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judul);
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("Pencarian dengan judul"); // set the top title
//        actionBar.setHomeButtonEnabled(true);
        final DodingDbQuery dbHelper = new DodingDbQuery(this);
        dbHelper.open();

        final EditText editText = findViewById(R.id.noSearch);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    return false;

                }
                return false;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() >= 3 ){
                    String sql="select * from doding where judul like '%"+s+"%'";
                    dodingList = dbHelper.getAllDoding(sql);
                    dodingCount = dodingList.size();
                    if(dodingCount ==0){
                        editText.setError( "Data tidak ditemukan" );
                    }
                    list = (ListView) findViewById(R.id.listNya);
                    JudulAdapter adapter = new JudulAdapter(JudulActivity.this,R.layout.model_item,dodingList);
                    list.setAdapter(adapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String stringText;

                            TextView textview=(TextView)view.findViewById(R.id.textId);
                            stringText=textview.getText().toString();
                            Intent intent = new Intent(JudulActivity.this,DodingActivity.class);
                            intent.putExtra("NO", stringText);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }
    public void changeFilter(View view)
    {
        Intent intent = new Intent(JudulActivity.this, NoActivity.class);
        startActivity(intent);
    }
}
