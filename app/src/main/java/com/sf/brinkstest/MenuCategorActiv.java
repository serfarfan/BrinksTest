package com.sf.brinkstest;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sf.brinkstest.models.App;
import com.sf.brinkstest.utils.Util;

import java.util.ArrayList;

public class MenuCategorActiv extends ListActivity {

    private final String TAG = "MenuCategorActiv";
    ArrayList<App> myApps = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("categories");
        for (String s : myList ) Log.d (TAG, "CAt " + s);

        //Inflate listView
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myList));
        myApps = (ArrayList<App>) getIntent().getSerializableExtra("apps");



    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        Intent i = new Intent(MenuCategorActiv.this, DetalleCategoriaActiv.class);
        Log.d(TAG, "Opc " + position);

        ArrayList<App> listToSend = new ArrayList<>();
        switch (position){
            case 0://music
                listToSend = getMusic();
                break;
            case 1://photo
                listToSend = getPhoto();
                break;
            case 2://reference
                listToSend = getReference();
                break;
            case 3://games
                listToSend = getGames();
                break;
            default:
                break;
        }

        i.putExtra("listToSend", listToSend);
        startActivity(i);
    }


    private ArrayList<App> getGames(){
        ArrayList<App> games = new ArrayList<>();
        for(App d : myApps){
            if(d.getCategory() != null && d.getCategory().contains("Games"))
            games.add(d);
        }
        return games;
    }

    private ArrayList<App> getReference(){
        ArrayList<App> games = new ArrayList<>();
        for(App d : myApps){
            if(d.getCategory() != null && d.getCategory().contains("Reference"))
                games.add(d);
        }
        return games;
    }
    private ArrayList<App> getPhoto(){
        ArrayList<App> games = new ArrayList<>();
        for(App d : myApps){
            if(d.getCategory() != null && d.getCategory().contains("Photo"))
                games.add(d);
        }
        return games;
    }
    private ArrayList<App> getMusic(){
        ArrayList<App> games = new ArrayList<>();
        for(App d : myApps){
            if(d.getCategory() != null && d.getCategory().contains("Music"))
                games.add(d);
        }
        return games;
    }


}
