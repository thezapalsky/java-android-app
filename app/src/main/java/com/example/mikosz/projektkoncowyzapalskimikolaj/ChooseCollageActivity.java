package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ChooseCollageActivity extends Activity {

    private LinearLayout kolarz_jeden;
    private LinearLayout kolarz_dwa;
    private LinearLayout kolarz_trzy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_collage);

        Log.d("kolaz", "stworzono");

        kolarz_jeden = (LinearLayout) findViewById(R.id.kolaz_jeden);
        kolarz_dwa = (LinearLayout) findViewById(R.id.kolaz_dwa);
        kolarz_trzy = (LinearLayout) findViewById(R.id.kolaz_trzy);

        kolarz_jeden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs.set_ktory_kolaz(1);
                Intent intent = new Intent(ChooseCollageActivity.this,CollageActivity.class);
                startActivity(intent);
            }
        });
        kolarz_dwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs.set_ktory_kolaz(2);
                Intent intent = new Intent(ChooseCollageActivity.this,CollageActivity.class);
                startActivity(intent);
            }
        });
        kolarz_trzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs.set_ktory_kolaz(3);
                Intent intent = new Intent(ChooseCollageActivity.this,CollageActivity.class);
                startActivity(intent);
            }
        });
    }
}
