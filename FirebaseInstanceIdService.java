package com.hackathon.chanchalroshan.openvote;

import android.support.annotation.Nullable;
import android.support.constraint.solver.*;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by amitroshan on 15/08/17.
 */

public class FirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
        //Toast.makeText(getApplicationContext(),token,Toast.LENGTH_SHORT).show();

        pushToServer(token);
    }

    public void pushToServer(String token) {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("Token", token)
                .add("Key", Cache.getPublic_key())
                .build();

        Request request = new Request.Builder()
                .url("http://techtodate.000webhostapp.com/hackathon_registerToken.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
