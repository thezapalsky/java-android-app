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
 * Created by 4ib1 on 2016-11-18.
 */
public class DrawerArrayAdapter extends ArrayAdapter {

    private String[] array;
    private Context _context;
    private int _resource;

    public DrawerArrayAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        this.array = objects;
        this._context = context;
        this._resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        //inflater - klasa konwertująca xml na kod javy
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //convertView = inflater.inflate(R.layout.drawer_cell, null);
        convertView = inflater.inflate(_resource, null);
        //szukam kontrolki w layoucie
        ImageView iv1 = (ImageView) convertView.findViewById(R.id.duno);

        if(position==0) {
            TextView tv1 = (TextView) convertView.findViewById(R.id.ddos);
            tv1.setText("font");
            iv1.setImageResource(R.drawable.pallet);

        }
        else {
            TextView tv1 = (TextView) convertView.findViewById(R.id.ddos);
            tv1.setText("coś innego");

        }
            //


        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // klik w obrazek
                Log.d("szufladka","sztos, dziala");
            }
        });

        return convertView;

    }
}
