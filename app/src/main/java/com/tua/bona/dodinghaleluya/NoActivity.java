package com.tua.bona.dodinghaleluya;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class NoActivity extends AppCompatActivity {
    EditText editText;
    private ArrayList<Doding> dodingList = new ArrayList<Doding>();
    WebView webDoding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no);
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pencarian dengan nomor"); // set the top title

        editText = findViewById(R.id.noSearch);
        webDoding =findViewById(R.id.webDodingNo);
        final DodingDbQuery dbHelper = new DodingDbQuery(this);
        dbHelper.open();
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if( TextUtils.isEmpty(editText.getText())){
                        editText.setError( "No wajib diisi" );
                    }else {
                        final String txt = String.valueOf(editText.getText());
                        final String sql = "select * from doding where no ='" + txt + "'";
                        String lirik = "";
                        dodingList = dbHelper.getAllDoding(sql);
                        lirik += "<b>" + String.valueOf(dodingList.get(0).getNo()) + ". " + dodingList.get(0).getJudul() + "</b><br>";
                        lirik += "<i>(" + dodingList.get(0).getKategori() + ")</i><br><br>";
                        lirik += dodingList.get(0).getLirik();
                        webDoding.loadData(lirik, "text/html; charset=utf-8", "utf-8");
                    }
                    return true;

                }
                return false;
            }
        });
//
        editText.setFilters(new InputFilter[]{new InputFilterMinMax("1", "506")});

    }
    public void changeFilter(View view)
    {
        Intent intent = new Intent(NoActivity.this, JudulActivity.class);
        startActivity(intent);
    }
    public class InputFilterMinMax implements InputFilter {
        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}
