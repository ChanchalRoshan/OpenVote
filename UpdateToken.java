package com.hackathon.chanchalroshan.openvote;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by amitroshan on 16/08/17.
 */

public class UpdateToken extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... urls) {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("Token", urls[0])
                .add("Key", Cache.getPublic_key())
                .build();

        Request request = new Request.Builder()
                .url("http://techtodate.000webhostapp.com/hackathon_registerToken.php")
                .post(body)
                .build();

        try {
            Response rp = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
