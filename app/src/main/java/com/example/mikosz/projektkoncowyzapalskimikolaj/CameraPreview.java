package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by 4ib1 on 2016-09-29.
 */



public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private Camera _camera;
    private SurfaceHolder _surfaceHolder;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        this._camera = camera;
        this._surfaceHolder = this.getHolder();
        this._surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            _camera.setPreviewDisplay(_surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        _camera.startPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        try {
            _camera.setPreviewDisplay(_surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        _camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
