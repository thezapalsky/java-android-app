package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;
import java.util.function.Function;

public class CameraActivity extends Activity {

    private String[] arr;
    private GridView gV;
    private File[] files;
    private int filesLength;
    private File pic;
    private File nazwisko;
    private File dir;
    private Button btTest;


    @Override
    protected void onRestart() {
        super.onRestart();
        generateView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        final Intent takePhoto = new Intent(CameraActivity.this, takePhoto.class);

        btTest = (Button) findViewById(R.id.btTest);
        btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(takePhoto);
            }
        });


        generateView();
    }



    void generateView(){
        gV = (GridView) findViewById(R.id.cameraGrid);

        pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        nazwisko = new File(pic, "zapalski");
        dir = new File(nazwisko, "miejsca");

        files = nazwisko.listFiles();
        filesLength = files.length;
        Arrays.sort(files);
        Log.d("tablica", String.valueOf(files));

        arr = new String[filesLength];
        for(int i=0;i<filesLength;i++){
            arr[i]=files[i].getName();
            Log.d("elo",arr[i]);
        }

        MyArrayAdapter adapter = new MyArrayAdapter(
                CameraActivity.this,
                R.layout.cameracell,
                arr
        );
        gV.setAdapter(adapter);
        //domyslny folder
        Prefs.setFolderinio( files[0].toString() );

        gV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.wtf("TAG","index = " + files[i]);
                Prefs.setFolderinio( files[i].toString() );
                Toast.makeText(CameraActivity.this, "folder do zapisu:" + Prefs.getFolderinio(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void deleteAlbum(final String albumName){
       // File mainFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Uwaga!");
        alert.setMessage("Czy na pewno chcesz usunac album "+albumName+"?");
//ok
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //wyswietl which
                File toDel = new File(nazwisko,albumName);
                toDel.delete();
                onRestart();
            }

        });

//no
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //wyswietl which
            }
        });
//
        alert.show();


        //Log.d("us","tak");
    }


}
