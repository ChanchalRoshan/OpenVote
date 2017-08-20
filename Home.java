package com.hackathon.chanchalroshan.openvote;

//Chanchal Roshan

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.constraint.solver.*;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.*;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Home extends AppCompatActivity implements View.OnClickListener {

    RadioGroup radioGroup;
    Button vote_now, vote_ledger;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Home");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseDatabase.getInstance().getReference();

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

        //referencing the widgets
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        vote_now = (Button) findViewById(R.id.vote_now);
        vote_ledger = (Button) findViewById(R.id.vote_ledger);
        vote_now.setOnClickListener(this);
        vote_ledger.setOnClickListener(this);

        if( Cache.voted == 1 ){
            vote_now.setEnabled(false);
            vote_now.setText("Thank You for Voting");
            vote_now.setBackgroundColor(Color.WHITE);
            vote_ledger.setVisibility(View.VISIBLE);
        }
        else {

            final AlertDialog.Builder add_event = new AlertDialog.Builder(this);
            final LayoutInflater layoutInflater = getLayoutInflater();
            View v = layoutInflater.inflate(R.layout.verified, null);
            add_event.setView(v);
            add_event.setTitle("Verified");
            add_event.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            add_event.show();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.vote_now:

                try {
                    int selectId = radioGroup.getCheckedRadioButtonId();
                    RadioButton checked = (RadioButton) findViewById(selectId);
                    //Toast.makeText(getApplicationContext(), checked.getText(), Toast.LENGTH_SHORT).show();

                    //maintaining intial node
                    Block transaction = new Block(String.valueOf(Calendar.getInstance().getTime()),
                            checked.getText().toString(), "");

                    mDatabase.push().setValue(transaction);

                    //pushing transactions to all nodes connected to app
                    new BroadcastSender().execute(transaction.getTimestamp(),
                            transaction.getVote(),
                            transaction.getHash(),
                            transaction.getPreviousHash());

                    vote_now.setEnabled(false);
                    vote_now.setText("Thank You for Voting");
                    vote_now.setBackgroundColor(Color.WHITE);
                    vote_ledger.setVisibility(View.VISIBLE);

                    Cache.voted = 1;
                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Select a Party...", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.vote_ledger:
                    startActivity(new Intent(getApplicationContext(), OpenChain.class));
                break;
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
}
