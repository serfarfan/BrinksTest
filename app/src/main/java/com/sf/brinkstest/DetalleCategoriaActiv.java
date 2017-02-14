package com.sf.brinkstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;

import com.sf.brinkstest.adapters.Adapter;
import com.sf.brinkstest.models.App;

import java.util.ArrayList;
import java.util.List;

public class DetalleCategoriaActiv extends AppCompatActivity {

    final String TAG = "DetalleCategoriaActiv";
    ListView listView ;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_detalle_categoria);


        ArrayList<App> listToSend = (ArrayList<App>) getIntent().getSerializableExtra("listToSend");
        for (App a: listToSend) Log.d(TAG, "Es " + a.getName());

        listView = (ListView)findViewById(R.id.list);
        adapter = new Adapter(getApplicationContext(), listToSend);
        listView.setAdapter(adapter);

    }
}
