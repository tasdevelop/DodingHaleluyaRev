package com.tua.bona.dodinghaleluya;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.logo)
                .setDescription("Versi digital Doding Haleluya")
                .addItem(new Element().setTitle("Version 1.0"))
                .addGroup("Terhubungan dengan pembuat")
                .addEmail("bonaxcrimo@gmail.com")
                .addWebsite("http://elitzone.com")
                .addPlayStore("com.tua.bona.dodinghaleluya")
                .addInstagram("bonatuamanihuruk")
                .addItem(createCopyright())
                .create();


        setContentView(aboutPage);
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("Tentang Aplikasi"); // set the top title

    }

    private Element createCopyright() {
        final Element copy = new Element();
        final String copyText = String.format("Copyright @%d by BonzDev", Calendar.getInstance().get(Calendar.YEAR));
        copy.setTitle(copyText);
        copy.setGravity(Gravity.CENTER);
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutActivity.this,copyText,Toast.LENGTH_SHORT).show();
            }
        });
        return copy;


    }
}
