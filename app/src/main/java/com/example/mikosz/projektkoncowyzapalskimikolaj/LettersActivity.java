package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;

public class LettersActivity extends Activity {

    private String[] lista;
    private RelativeLayout relatywny_glowny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters);
        final LinearLayout ll_w_lv = (LinearLayout) findViewById(R.id.ll_w_sv);
        ImageView koleczko = (ImageView) findViewById(R.id.koleczko);

        AssetManager assetManager = getAssets();
        try {
            lista = assetManager.list("fonts"); // fonts to nazwa podfolderu w assets
        }catch(IOException e) {
            e.printStackTrace();
        }

        Log.d("szufladka",lista[1]);

        for(int i=0; i<lista.length; i++) {
            Log.d("szufladka",lista[i]);
            Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/"+lista[i]);
            TextView textView = new TextView(LettersActivity.this);
            textView.setText(lista[i]);
            textView.setTypeface(tf);
            ll_w_lv.addView(textView);


        }

        koleczko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout relatywny_glowny = (RelativeLayout) findViewById(R.id.glowny_r);
                MyColorPicker colorPicker = new MyColorPicker(LettersActivity.this);
                relatywny_glowny.addView(colorPicker);
            }
        });


    }
}
