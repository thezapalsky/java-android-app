package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.mikosz.projektkoncowyzapalskimikolaj.CameraPreview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class takePhoto extends AppCompatActivity {


    private Camera camera;
    private int cameraId = -1;
    private CameraPreview _cameraPreview;
    private FrameLayout _frameLayout;
    private Button takePhotoBt;
    private Button resBt;
    private Button saveBt;
    private File pic;
    private File nazwisko;
    private File dir;
    private byte[] fdata;
    private String folderinio;
    private LinearLayout efekty;
    private Button balansBt;
    private Button lightBt;
    private Button kolorystykaBt;
    private Button sizeBt;
    private Camera.Parameters camParams;
    List<String> lista = new ArrayList<String>();
    List<Camera.Size> lista2 = new ArrayList<Camera.Size>();
    private String[] tab;
    private String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //nazwisko = new File(pic, "zapalski");

        setContentView(R.layout.activity_take_photo);
        takePhotoBt = (Button) findViewById(R.id.takePhotoBt);
        resBt = (Button) findViewById(R.id.reset);
        saveBt = (Button) findViewById(R.id.save);
        sizeBt = (Button) findViewById(R.id.size);
        balansBt = (Button) findViewById(R.id.balans);
        lightBt = (Button) findViewById(R.id.swiatlo);
        kolorystykaBt = (Button) findViewById(R.id.kolorystyka);
        efekty = (LinearLayout) findViewById(R.id.wybor_efektu);


        balansBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tba
                camParams = camera.getParameters();
                lista = camParams.getSupportedWhiteBalance();
                final String[] tab = new String[lista.size()];
                for(int i=0; i<lista.size();i++)
                {
                    tab[i]=lista.get(i);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(takePhoto.this);
                alert.setTitle("Balans");
                alert.setItems(tab, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Log.d("tag1", tab[which]);
                        camParams.setWhiteBalance(tab[which]);
                        camera.setParameters(camParams);
                    }
                });
//
                alert.show();

                ObjectAnimator anim = ObjectAnimator.ofFloat(efekty, View.TRANSLATION_Y, 200);
                anim.setDuration(300); //ms
                //anim.start();
            }
        });

        lightBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int min = camParams.getMaxExposureCompensation();
                Log.d("expo", Integer.toString(min));

            }
        });

        kolorystykaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camParams = camera.getParameters();
                lista = camParams.getSupportedColorEffects();
                final String[] tab = new String[lista.size()];
                for(int i=0; i<lista.size();i++)
                {
                    tab[i]=lista.get(i);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(takePhoto.this);
                alert.setTitle("Kolorystyka");
                alert.setItems(tab, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        camParams.setColorEffect(tab[which]);
                        camera.setParameters(camParams);
                    }
                });
//
                alert.show();
            }
        });

        sizeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camParams = camera.getParameters();
                //Log.d("para123", camParams.getSupportedPictureSizes().toString());
                lista2 = camParams.getSupportedPictureSizes();
                final String[] tab = new String[lista2.size()];
                for(int i=0; i<lista2.size();i++)
                {
                    tab[i]= String.valueOf(lista2.get(i).width) +"x"+String.valueOf(lista2.get(i).height);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(takePhoto.this);
                alert.setTitle("Rozmiar");
                alert.setItems(tab, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Log.d("TAG", tab[which].split("x")[1]);
                        int width = Integer.parseInt( tab[which].split("x")[0]);
                        int height = Integer.parseInt( tab[which].split("x")[1]);
                        camParams.setPictureSize(width,height);
                        camera.setParameters(camParams);
                    }
                });
//
                alert.show();
            }
        });

        takePhotoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera.takePicture(null, null, camPictureCallback);
                saveBt.setVisibility(View.VISIBLE);
            }
        });

        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("klik",dir.getPath());
                folderinio = Prefs.getFolderinio();
                dir = new File(folderinio);
                SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String d = dFormat.format(new Date());
                File myFoto = new File(dir, d + ".jpg");
                Log.d("patha", String.valueOf(myFoto));
                FileOutputStream fs = null;
                try {
                    fs = new FileOutputStream(myFoto);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    fs.write(fdata); //zapisuje tylko pierwsze zrobione
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        initCamera();
        initPreview();
    }


    @Override
    protected void onPause() {
        super.onPause();
        // jeśli nie zwolnimy (release) kamery
        //inna aplikacje nie może jej używać

        if (camera != null) {
            camera.stopPreview();
            //linijka nieudokumentowana w API, bez niej jest crash przy wznawiamiu kamery
            _cameraPreview.getHolder().removeCallback(_cameraPreview);
            camera.release();
            camera = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (camera == null) {
            //zainicjalizuj kamerę
            //od nowa
            initCamera();
            initPreview();
        }
    }

    protected void initCamera() {
        boolean cam = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);

        if (!cam) {
            // uwaga - brak kamery

        } else {

            // wykorzystanie danych zwróconych przez kolejną funkcję getCameraId

            cameraId = getCameraId();
            // jest jakaś kamera!
            if (cameraId < 0) {
                // brak kamery z przodu!
            } else if (cameraId >= 0) {
                camera = Camera.open(cameraId);
            }

        }
    }

    protected int getCameraId() {
        int cid = 0;
        int camerasCount = Camera.getNumberOfCameras(); // gdy więcej niż jedna kamera

        for (int i = 0; i < camerasCount; i++) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);

            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cid = i;
            }
        /*
            if (cameraInfo.facing == CameraInfo.CAMERA_FACING_FRONT) {
                cid = i;
            }
	    */
        }

        return cid;
    }

    protected void initPreview() {
        _cameraPreview = new CameraPreview(takePhoto.this, camera);
        _frameLayout = (FrameLayout) findViewById(R.id.frameLayout1);
        _frameLayout.addView(_cameraPreview);

    }

    private Camera.PictureCallback camPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            // zapisz dane zdjęcia w tablicy typu byte[]
            // do poźniejszego wykorzystania
            // poniewaz zapis zdjęcia w galerii powinien być dopiero po akceptacji butonem

            fdata = data;
            Log.d("data", String.valueOf(fdata));
            // odswiez kamerę (zapobiega przycięciu się kamery po zrobieniu zdjęcia)

            camera.startPreview();

        }
    };
}
