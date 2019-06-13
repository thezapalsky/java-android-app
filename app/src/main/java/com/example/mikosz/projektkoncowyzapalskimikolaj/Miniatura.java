package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by 4ib1 on 2016-10-21.
 */
public class Miniatura extends ImageView {

    public Bitmap smallBmp;

    public Miniatura(Context context, Bitmap bmp,int size) {
        super(context);
        this.setImageBitmap(bmp);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
        this.setLayoutParams(layoutParams);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.argb(255, 255, 255, 255));
        canvas.drawRect(0,0,200,200,paint);
        //Log.d("ddd", "dupa123");
    }
}
