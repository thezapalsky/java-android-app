package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.VectorDrawable;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by 4ib1 on 2016-12-02.
 */
public class MyColorPicker extends RelativeLayout {

    public MyColorPicker(Context context) {
        super(context);
        LinearLayout ll = new LinearLayout(context);
        ImageView iv = new ImageView(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        iv.setImageResource(R.drawable.colors);
        ll.setBackgroundColor(0x66000000);
        ll.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout ll_butts = new LinearLayout(context);
        ll_butts.setOrientation(LinearLayout.HORIZONTAL);
        ll_butts.setBackgroundColor(Color.parseColor("#abcdef"));

        Button b1  = new Button(context);
        b1.setText("zatwierdz");
        b1.setWidth(Prefs.getSzerokosc() / 2);

        Button b2  = new Button(context);
        b2.setText("anuluj");
        b2.setWidth(Prefs.getSzerokosc() / 2);

        b1.setBackgroundColor(Color.parseColor("#ff0000"));

        LinearLayout podglad = new LinearLayout(context);
        podglad.setBackgroundColor(Color.parseColor("#fedcba"));
        podglad.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));


        ll_butts.addView(b1);
        ll_butts.addView(b2);
        ll.addView(iv);
        ll.addView(ll_butts);
        ll.addView(podglad);
        this.addView(ll);
    }

}
