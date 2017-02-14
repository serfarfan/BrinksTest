package com.sf.brinkstest.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by sf on 2/13/17.
 */

public class AsyncJson extends AsyncTask<String,Void,String> {


    private int codeRequest;
    private String url;
    private final String TAG = "AsyncJSON";
    public Context context;

    //******************CONSTRUCTOR**************
    public AsyncJson(String url, int codeRequest, Context context) {
        Log.d(TAG, "Llega al AsyncJSON");
        this.url = url;
        this.codeRequest = codeRequest;
        this.context = context;
    }

    //*********************OVERRIDE METHODS*********************

    @Override
    protected String doInBackground(String... strings) {
        String result = readJSONFeed(url);
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        MyBus.getInstance().post(new AsyncTaskStringResult(result,codeRequest));
    }

    //******************PROPER METHODS**************

    public String readJSONFeed(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URL);
        try {
            HttpResponse response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                if (stringBuilder.length()>0) stringBuilder.delete(0,stringBuilder.length()-1);
                stringBuilder.insert(0,"{\"error\":"+statusCode+"}");
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            if (stringBuilder.length()>0) stringBuilder.delete(0,stringBuilder.length()-1);
            stringBuilder.insert(0,"{\"error\":-1}");
            //Log.e("readJSONFeed", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }

}
