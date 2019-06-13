package com.example.mikosz.projektkoncowyzapalskimikolaj;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class FotoActivity extends Activity {

    private String[] array = {"aaa","bbb"};
    private ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        lv1 = (ListView) findViewById(R.id.listview);

        DrawerArrayAdapter adapter = new DrawerArrayAdapter(
                FotoActivity.this,
                R.layout.drawer_cell,
                array
        );
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(FotoActivity.this,LettersActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
