package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class AlbumViewAcitvity extends Activity {

    private LinearLayout linear1;
    private LinearLayout LL;
    private ImageView adam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_view_acitvity);
        ///
        adam = (ImageView) findViewById(R.id.adam);

        Bundle bundle = getIntent().getExtras();
        String wartosc = bundle.getString("key").toString();

        File file = new File(wartosc);
        File[] obrazky = file.listFiles();

        linear1 = (LinearLayout) findViewById(R.id.tu_bd_zdjecia);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Prefs.setSzerokosc(width);
        Prefs.setWysokosc(height);
        int odd = 0;

        ViewGroup.LayoutParams lparams;

        if (obrazky.length % 2 == 0) {
            for (int i = 0; i < obrazky.length; i += 2) {

                LinearLayout LL = new LinearLayout(this);
                LL.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LL.setLayoutParams(LLParams);
                linear1.addView(LL);

                if (odd == 2) {
                    lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2);
                    odd = 1;
                } else {
                    lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
                    odd = 2;
                }

                if (obrazky[i].isFile()) {
                    ImageView imgv01 = new ImageView(this);
                    String imagepath = obrazky[i].getPath();
                    Bitmap bmp = betterImageDecode(imagepath);
                    LL.addView(imgv01);
                    imgv01.setImageBitmap(bmp);
                    imgv01.setLayoutParams(lparams);
                    imgv01.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }

                if (odd == 2) {
                    lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2);
                } else {
                    lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
                }
                if (obrazky[i + 1].isFile()) {
                    ImageView imgv01 = new ImageView(this);
                    String imagepath = obrazky[i + 1].getPath();
                    Bitmap bmp = betterImageDecode(imagepath);
                    LL.addView(imgv01);
                    imgv01.setImageBitmap(bmp);
                    imgv01.setLayoutParams(lparams);
                    imgv01.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
            }

        }
        else
        {

            for (int i = 0; i < obrazky.length; i ++) {

                LinearLayout LL = new LinearLayout(this);
                LL.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams LLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LL.setLayoutParams(LLParams);
                linear1.addView(LL);

                 lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);


                if (obrazky[i].isFile()) {
                    ImageView imgv01 = new ImageView(this);
                    String imagepath = obrazky[i].getPath();
                    Bitmap bmp = betterImageDecode(imagepath);
                    LL.addView(imgv01);
                    imgv01.setImageBitmap(bmp);
                    imgv01.setLayoutParams(lparams);
                    imgv01.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
            }

        }

        adam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //odpala adama na full skrinie
                Intent intent = new Intent(AlbumViewAcitvity.this,FotoActivity.class);
                startActivity(intent);
            }
        });

    }

    private Bitmap betterImageDecode(String filePath) {

        Bitmap myBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();    //opcje przekształcania bitmapy
        options.inSampleSize = 4; // zmniejszenie jakości bitmapy 4x
        //
        myBitmap = BitmapFactory.decodeFile(filePath, options);
        return myBitmap;
    }
}


