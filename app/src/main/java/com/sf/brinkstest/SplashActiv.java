package com.sf.brinkstest;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.sf.brinkstest.async.AsyncJson;
import com.sf.brinkstest.models.App;
import com.sf.brinkstest.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class SplashActiv extends AppCompatActivity {


    // Constant for splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private Context ctx;
    private final String TAG = "SplashActiv";
    private final String URL = "https://itunes.apple.com/us/rss/toppaidapplications/limit=20/json";
    private final int REQUEST_CODE = 112;
    private HashMap<String,App> data = new HashMap<>();
    public Set<String> categories = new HashSet<>();
    public Set<App> appsAll = new HashSet<>();

    //***********************OVERRIDE METHODS************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //run method will be executed when timer is over
                Intent i = new Intent(SplashActiv.this, MenuCategorActiv.class);
                ArrayList<String> list = new ArrayList<>();
                list.addAll(categories);
                i.putExtra("categories", list);
                ArrayList<App> apps = new ArrayList<>();
                apps.addAll(appsAll);
                i.putExtra("apps", apps);
                startActivity(i);
                //Close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

        //Validate internet access
        ctx=this;
        if (Util.hasInternetConnectivity(ctx)){
            //CONSUME JSON
            try {
                readJson();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Toast.makeText(ctx, "Hay internet", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(ctx, "Por favor verifique su conexión a internet", Toast.LENGTH_LONG).show();
        }

    }


    //***********************PROPER METHODS************************

    private  void readJson() throws JSONException{
        //Call AsyncTask class
        Log.d(TAG, "Llega al read");
        String result = "";
        try{
            result = new AsyncJson(URL, REQUEST_CODE, ctx).execute().get();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException ex){
            ex.printStackTrace();
        }
        Log.d(TAG, "El result es "+result);
        //Parse Json
        JSONObject reader = new JSONObject(result);
        JSONObject feed  = reader.getJSONObject("feed");
        JSONArray entry = feed.getJSONArray("entry");
        //Looping through All Apps
        for (int k = 0;  k < entry.length(); k++){
            JSONObject app = entry.getJSONObject(k);
            JSONObject name = app.getJSONObject("im:name");
            String nameApp = name.getString("label");
            Log.d(TAG, "name " + nameApp);
            //Get url image
            JSONArray image = app.getJSONArray("im:image");
            JSONObject image100 = image.getJSONObject(2);
            String urlImage = image100.getString("label");
            Log.d(TAG, "img " + urlImage);
            //Get vendor
            JSONObject vendor = app.getJSONObject("im:artist");
            String vendorName = vendor.getString("label");
            Log.d(TAG, "vendorName " + vendorName);
            //Get category
            JSONObject category = app.getJSONObject("category");
            JSONObject attributes = category.getJSONObject("attributes");
            String txtCat = attributes.getString("term");
            Log.d(TAG, "txtCat " + txtCat);
            //Get cost
            JSONObject cost = app.getJSONObject("im:price");
            String txtCost = cost.getString("label");
            Log.d(TAG, "txtCost " + txtCost);
            //Get summary
            JSONObject summary = app.getJSONObject("summary");
            String txtSummary = summary.getString("label");
            Log.d(TAG, "txtSummary " + txtSummary);
            //Create app object in model
            App appToCreate = new App(nameApp, urlImage, vendorName, txtCat, txtCost, txtSummary);
            //Add this club to hashMap
            data.put(nameApp, appToCreate);
            appsAll.add(appToCreate);
            categories.add(txtCat);
        }

        for (String s : categories) {
            Log.d(TAG, "Cats es " + s);
        }


    }
}
