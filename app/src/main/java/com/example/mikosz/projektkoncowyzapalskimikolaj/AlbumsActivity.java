package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class AlbumsActivity extends Activity {

    private GridView gv1;
    //private ImageView iv1;
    private ArrayList<String> array = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        gv1 = (GridView) findViewById(R.id.gv1);
        //iv1 = (ImageView) findViewById(R.id.iv1);

        //iv1.setImageResource(R.drawable.folder);

        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //file.listFiles();
        File zap = new File(file.getPath(), "zapalski");

        final File[] tablica = zap.listFiles();     //tablica plików
        //files.length                         // ilośc plików
        Arrays.sort(tablica);                     // sortowanie plików wg nazwy

        for(int i=0; i<tablica.length;i++)
        {
            array.add( tablica[i].getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                AlbumsActivity.this,     // Context
                R.layout.cell,     // nazwa pliku xml naszej komórki
                R.id.tv1,         // id pola txt w komórce
                 array );         // tablica przechowująca dane


                gv1.setAdapter(adapter);

        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //test
                String folder = tablica[i].toString();
                //Log.wtf("TAG","index = " + i);
                Intent intent = new Intent(AlbumsActivity.this,AlbumViewAcitvity.class);
                intent.putExtra("key",folder);
                startActivity(intent);

            }
        });
    }
}
