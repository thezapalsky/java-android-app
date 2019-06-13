package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by 4ib1 on 2016-10-14.
 */
public class Kolo extends View {
    public Kolo(Context context) {
        super(context);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.argb(150, 255, 255, 255));
        canvas.drawCircle(Prefs.getSzerokosc()/2, Prefs.getWysokosc()/2, 200, paint);
    }
}
