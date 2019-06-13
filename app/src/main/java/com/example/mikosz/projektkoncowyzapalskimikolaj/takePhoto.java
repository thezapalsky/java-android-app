package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.hardware.Camera;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
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
    private LinearLayout dolny;
    private Button balansBt;
    private Button lightBt;
    private Button kolorystykaBt;
    private Button sizeBt;
    private Camera.Parameters camParams;
    List<String> lista = new ArrayList<String>();
    List<Camera.Size> lista2 = new ArrayList<Camera.Size>();
    private String[] tab;
    private String test;
    private Kolo circle;
    private Miniatura miniatura;
    private OrientationEventListener orientationEventListener;
    private Boolean pion = true;
    private Boolean zdj_zrobione = false;
    private Bitmap bitmap;

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
        dolny = (LinearLayout) findViewById(R.id.dolny_pasek);



        /*
        cam.set("orientation", "portrait");
        camera.setParameters(camParams);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            camParams.set("orientation", "portrait");
            camParams.set("rotation",90);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            camParams.set("orientation", "landscape");
            camParams.set("rotation", 90);
        }

        */
        balansBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tba
                camParams = camera.getParameters();
                lista = camParams.getSupportedWhiteBalance();
                final String[] tab = new String[lista.size()];
                for (int i = 0; i < lista.size(); i++) {
                    tab[i] = lista.get(i);
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


            }
        });

        lightBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camParams = camera.getParameters();
                int min = camParams.getMinExposureCompensation();
                int max = camParams.getMaxExposureCompensation();
                //Log.d("expo", Integer.toString(min));
                //Log.d("expo", Integer.toString(max));
                final String[] tab = new String[max * 2 + 1];
                for (int i = 0; i <= max - min; i++) {
                    tab[i] = Integer.toString(i + min);
                    Log.d("num", tab[i]);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(takePhoto.this);
                alert.setTitle("naswietlenie");
                alert.setItems(tab, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        camParams.setExposureCompensation(Integer.parseInt(tab[which]));
                        camera.setParameters(camParams);
                    }
                });
                alert.show();

            }
        });

        kolorystykaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camParams = camera.getParameters();
                lista = camParams.getSupportedColorEffects();
                final String[] tab = new String[lista.size()];
                for (int i = 0; i < lista.size(); i++) {
                    tab[i] = lista.get(i);
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
                for (int i = 0; i < lista2.size(); i++) {
                    tab[i] = String.valueOf(lista2.get(i).width) + "x" + String.valueOf(lista2.get(i).height);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(takePhoto.this);
                alert.setTitle("Rozmiar");
                alert.setItems(tab, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Log.d("TAG", tab[which].split("x")[1]);
                        int width = Integer.parseInt(tab[which].split("x")[0]);
                        int height = Integer.parseInt(tab[which].split("x")[1]);
                        camParams.setPictureSize(width, height);
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

        orientationEventListener = new OrientationEventListener(takePhoto.this) {
            @Override
            public void onOrientationChanged(int i) {
                // i zwraca kąt 0 - 360 stopni podczas obracania ekranem w osi Z
                // tutaj wykonaj animacje butonów i miniatur zdjęć
                Log.d("anim", new Integer(i).toString());
                if (i > 45 && i < 180 && pion) {
                    pion = false;
                    Log.d("pion", "wykonuje sie raz");

                    ObjectAnimator.ofFloat(kolorystykaBt, View.ROTATION, 0, -90)
                            .setDuration(300)
                            .start();
                    ObjectAnimator.ofFloat(saveBt, View.ROTATION, 0, -90)
                            .setDuration(300)
                            .start();
                    ObjectAnimator.ofFloat(resBt, View.ROTATION, 0, -90)
                            .setDuration(300)
                            .start();
                    ObjectAnimator.ofFloat(takePhotoBt, View.ROTATION, 0, -90)
                            .setDuration(300)
                            .start();
                    ObjectAnimator.ofFloat(balansBt, View.ROTATION, 0, -90)
                            .setDuration(300)
                            .start();
                    ObjectAnimator.ofFloat(sizeBt, View.ROTATION, 0, -90)
                            .setDuration(300)
                            .start();
                    ObjectAnimator.ofFloat(lightBt, View.ROTATION, 0, -90)
                            .setDuration(300)
                            .start();
                    if (zdj_zrobione) {
                        ObjectAnimator.ofFloat(miniatura, View.ROTATION, 0, -90)
                                .setDuration(300)
                                .start();

                        Matrix matrix = new Matrix();
                        // rotate Bitmap
                        matrix.postRotate(90);
                        //zwracam nową obróconą
                        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                        bitmap = rotatedBitmap;
                        Log.d("AABC", "obrocono");
                    }
                }
                if (i < 45 && !pion) {
                    pion = true;
                    Log.d("pion", "wykonuje sie raz");

                    ObjectAnimator.ofFloat(kolorystykaBt, View.ROTATION, -90, 0)
                            .setDuration(300)
                            .start();
                    ObjectAnimator.ofFloat(saveBt, View.ROTATION, -90, 0)
                            .setDuration(300)
                            .start();
                    ObjectAnimator.ofFloat(resBt, View.ROTATION, -90, 0)
                            .setDuration(300)
                            .start();
                    ObjectAnimator.ofFloat(takePhotoBt, View.ROTATION, -90, 0)
                            .setDuration(300)
                            .start();
                    ObjectAnimator.ofFloat(balansBt, View.ROTATION, -90, 0)
                            .setDuration(300)
                            .start();
                    ObjectAnimator.ofFloat(sizeBt, View.ROTATION, -90, 0)
                            .setDuration(300)
                            .start();
                    ObjectAnimator.ofFloat(lightBt, View.ROTATION, -90, 0)
                            .setDuration(300)
                            .start();
                    if (zdj_zrobione) {
                        ObjectAnimator.ofFloat(miniatura, View.ROTATION, -90, 0)
                                .setDuration(300)
                                .start();
                    }
                }


            }
        };

        initCamera();
        initPreview();
    }


    @Override
    protected void onPause() {
        super.onPause();
        // jeśli nie zwolnimy (release) kamery
        //inna aplikacje nie może jej używać

        orientationEventListener.disable();

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
        if (orientationEventListener.canDetectOrientation()) {
            Log.d("lll", "lis dziala");
            orientationEventListener.enable();
        } else {
            Log.d("lll", "lis nie dziala");
        }

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
        final boolean[] animacja = {true};
        _cameraPreview = new CameraPreview(takePhoto.this, camera);
        _frameLayout = (FrameLayout) findViewById(R.id.frameLayout1);
        _frameLayout.addView(_cameraPreview);

        circle = new Kolo(takePhoto.this);
        _frameLayout.addView(circle);

        _frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("nwm","aaadziala");
                if (animacja[0]) {
                    ObjectAnimator anim = ObjectAnimator.ofFloat(efekty, View.TRANSLATION_Y, 0);
                    anim.setDuration(300); //ms
                    anim.start();
                    ObjectAnimator anim2 = ObjectAnimator.ofFloat(dolny, View.TRANSLATION_Y, 0);
                    anim2.setDuration(300); //ms
                    anim2.start();

                    animacja[0] = !animacja[0];
                } else {
                    ObjectAnimator anim = ObjectAnimator.ofFloat(efekty, View.TRANSLATION_Y, -100);
                    anim.setDuration(300); //ms
                    anim.start();
                    ObjectAnimator anim2 = ObjectAnimator.ofFloat(dolny, View.TRANSLATION_Y, 200);
                    anim2.setDuration(300); //ms
                    anim2.start();

                    animacja[0] = !animacja[0];
                }
            }
        });

    }

    private Camera.PictureCallback camPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            // zapisz dane zdjęcia w tablicy typu byte[]
            // do poźniejszego wykorzystania
            // poniewaz zapis zdjęcia w galerii powinien być dopiero po akceptacji butonem
            fdata = data;
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            final Bitmap smallBmp = Bitmap.createScaledBitmap(bitmap, 200, 200, false);
            miniatura = new Miniatura(takePhoto.this, smallBmp, 200);

            miniatura.setX(100);
            miniatura.setY(500);

            miniatura.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    Log.d("XX", "pos x: " + motionEvent.getRawX());

                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            miniatura.setX(motionEvent.getRawX() - 50);
                            //miniatura.setY(500);

                            if(motionEvent.getRawX() > 400)
                                _frameLayout.removeView(miniatura);
                            break;
                        case MotionEvent.ACTION_UP:
                            miniatura.setX(100);
                            break;
                    }
                    return false;
                }
            });

            miniatura.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //Log.d("klik","klik");

                    AlertDialog.Builder alert = new AlertDialog.Builder(takePhoto.this);
                    alert.setTitle("Uwaga!");
                    //nie może mieć setMessage!!!
                    final String[] jakie = {"zapisz", "usuń", "zapisz wszystkie [tba]", "usuń wszystkie [tba]"};
                    alert.setItems(jakie, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Log.d("klik",jakie[which]);

                            if (jakie[which] == "zapisz") {

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        folderinio = Prefs.getFolderinio();
                                        dir = new File(folderinio);
                                        SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                                        String d = dFormat.format(new Date());
                                        File myFoto = new File(dir, d + ".jpeg");
                                        //Log.d("patha", String.valueOf(myFoto));
                                        FileOutputStream fs = null;
                                        try {
                                            fs = new FileOutputStream(myFoto.getPath());
                                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fs);
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            fs.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, 1);
                                //tutaj pokazanie ekranu z informacją o zapisie lub progressa
                                Log.d("klik", "zapisano");
                            }
                            if (jakie[which] == "usuń") {

                                _frameLayout.removeView(miniatura);
                                Log.d("klik", "usunieto");

                            }

                        }
                    });
//
                    alert.show();

                    return false;
                }
            });

            _frameLayout.addView(miniatura);
            zdj_zrobione = true;
            //Log.d("dataxx", String.valueOf(fdata));
            // odswiez kamerę (zapobiega przycięciu się kamery po zrobieniu zdjęcia)

            camera.startPreview();

        }
    };
}
