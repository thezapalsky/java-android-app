package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 4ib1 on 2016-09-22.
 */
public class MyArrayAdapter extends ArrayAdapter {

    private String[] array;
    private Context _context;

    public MyArrayAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        array = objects;
        _context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        //inflater - klasa konwertująca xml na kod javy
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.cameracell, null);
        //szukam kontrolki w layoucie

        TextView tv1 = (TextView) convertView.findViewById(R.id.cameraTextField);
        tv1.setText(array[position]);
        //
        ImageView iv1 = (ImageView) convertView.findViewById(R.id.clickFile);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // klik w obrazek
                //Log.d("elo", array[position]);

            }
        });

        ImageView iv2 = (ImageView) convertView.findViewById(R.id.clickDelete);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // klik w obrazek
                Log.d("elo", "klik byl2");
                ((CameraActivity)_context).deleteAlbum(array[position]);
            }
        });

        return convertView;
    }
}
