package com.hackathon.chanchalroshan.openvote;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.*;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Verify extends AppCompatActivity implements View.OnClickListener{

    EditText aadhar_number;
    Button verified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        FirebaseMessaging.getInstance().subscribeToTopic("trans");
        final String token = FirebaseInstanceId.getInstance().getToken();

        new UpdateToken().execute(token);

        getSupportActionBar().setTitle("Verify");

        /*
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
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
                    Response rp = client.newCall(request).execute();
                    Toast.makeText(getApplicationContext(), String.valueOf(rp), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        */


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.package.ACTION_LOGOUT");
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("onReceive","Logout in progress");
                //At this point you should start the login activity and finish this one
                finish();
            }
        }, intentFilter);


        verified = (Button) findViewById(R.id.verify_button);
        aadhar_number = (EditText) findViewById(R.id.aadhar_verify_field);
        aadhar_number.setText("");
        verified.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.verify_button:
                /*final ProgressDialog pro = ProgressDialog.show(getApplication(),
                        "Verifying",
                        "Please Wait....", true);
                        */
                if( aadhar_number.getText().length() != 12 ){
                    Toast.makeText(this, "ERROR: Enter a valid aadhar number", Toast.LENGTH_SHORT).show();
                }
                else {
                    Cache.setAadhar_number(aadhar_number.getText().toString());
                    startActivity(new Intent(getApplicationContext(), Home.class));
                }
                //pro.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.user_account:
                //Toast.makeText(getApplicationContext(),"User",Toast.LENGTH_SHORT).show();
                //signOut();
                startActivity(new Intent(getApplicationContext(),UserDetails.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
