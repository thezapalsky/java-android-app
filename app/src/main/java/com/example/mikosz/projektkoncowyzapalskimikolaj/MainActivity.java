package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.content.Intent;
import android.graphics.Point;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private File file;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Prefs.setSzerokosc(width);
        Prefs.setWysokosc(height);

        //
        getSupportActionBar().hide();
        //
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //Toast.makeText(MainActivity.this, file.getPath() ,Toast.LENGTH_SHORT).show();
        File dir = new File(file.getPath(), "zapalski");
        if(!dir.exists())
            dir.mkdir();

        File miejsca = new File(dir.getPath(),"miejsca");
        if(!miejsca.exists())
            miejsca.mkdir();

        File osoby = new File(dir.getPath(),"osoby");
        if(!osoby.exists())
        osoby.mkdir();

        File rzeczy = new File(dir.getPath(),"rzeczy");
        if(!rzeczy.exists())
        rzeczy.mkdir();
        ////////////////
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);

        bt1.setText("kamera");
        bt2.setText("albumy");
        bt3.setText("kolaż");
        bt4.setText("web");


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AlbumsActivity.class);
                startActivity(intent);
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CameraActivity.class);
                startActivity(intent);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ChooseCollageActivity.class);
                startActivity(intent);
            }
        });


    }
}
