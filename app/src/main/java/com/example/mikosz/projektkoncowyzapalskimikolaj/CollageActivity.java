package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class CollageActivity extends Activity {

    private LinearLayout ten_slynny_kolaz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collage);

        int ktory = Prefs.get_ktory_kolaz();
        ten_slynny_kolaz = (LinearLayout) findViewById(R.id.layout_w_ktorym_bedzie_wyswietlony_kolaz);

        ImageView iv1 = new ImageView(this);
        ImageView iv2 = new ImageView(this);
        ImageView iv3 = new ImageView(this);
        ImageView iv4 = new ImageView(this);
        iv1.setImageResource(R.drawable.camera);
        iv2.setImageResource(R.drawable.camera);
        iv3.setImageResource(R.drawable.camera);
        iv4.setImageResource(R.drawable.camera);
        iv1.setBackgroundColor(0xffff0000);
        iv2.setBackgroundColor(0xff00ff00);
        iv3.setBackgroundColor(0xff0000ff);
        iv4.setBackgroundColor(0xffffff00);
        int aaa = Prefs.getWysokosc();
        int bbb = Prefs.getSzerokosc();

        switch (ktory){
            case 0:
                Log.d("kolaz","nie wybrano ???");
                break;
            case 1:
                Log.d("kolaz","powstaje nr1");
                ten_slynny_kolaz.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(bbb/2, aaa);
                ten_slynny_kolaz.addView(iv1, lp);
                ten_slynny_kolaz.addView(iv2, lp);
                break;
            case 2:
                Log.d("kolaz","powstaje nr2");
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(bbb, aaa/2);
                ten_slynny_kolaz.addView(iv1, lp2);
                ten_slynny_kolaz.addView(iv2, lp2);
                break;
            case 3:
                Log.d("kolaz","powstaje nr3");
                LinearLayout ll1 = new LinearLayout(this);
                ll1.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(bbb/3, aaa/3);
                ll1.addView(iv1, lp3);
                ll1.addView(iv2, lp3);
                ll1.addView(iv3, lp3);
                ten_slynny_kolaz.addView(ll1);
                LinearLayout ll2 = new LinearLayout(this);
                LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(bbb, 2*(aaa/3));
                ll2.addView(iv4, lp4);
                ten_slynny_kolaz.addView(ll2);
                break;
            default:
                Log.d("kolaz","???");
                break;
        }
    }
}
