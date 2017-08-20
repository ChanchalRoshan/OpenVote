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

public class BroadcastSender extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... urls) {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("timestamp", urls[0] )
                .add("vote", urls[1])
                .add("hash", urls[2])
                .add("previousHash", urls[3])
                .build();

        Request request = new Request.Builder()
                .url("http://techtodate.000webhostapp.com/pushTransactions.php")
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
