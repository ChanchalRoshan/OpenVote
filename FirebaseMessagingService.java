package com.hackathon.chanchalroshan.openvote;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {

        //Toast.makeText(getApplicationContext(), remoteMessage.getData().get("transaction"),Toast.LENGTH_SHORT).show();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {

            @Override
            public void run() {

                /*
                Toast.makeText(getApplicationContext(),
                        remoteMessage.getData().get("transaction").toString(),
                        Toast.LENGTH_SHORT).show();
                */

                Gson gson = new Gson();
                Block  b = gson.fromJson(remoteMessage.getData().get("transaction"), Block.class);
                //Toast.makeText(getApplicationContext(), b.getHash(), Toast.LENGTH_SHORT).show();
                //OpenChain.arrayList.add(b);
                Cache.b.add(b);
            }
        });
    }
}
