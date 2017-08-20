package com.hackathon.chanchalroshan.openvote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OpenChain extends AppCompatActivity {

    public static ArrayList<Block> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_chain);

        arrayList = new ArrayList<>();

        getSupportActionBar().setTitle("Vote Ledger");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView) findViewById(R.id.listView);

        BlockchainAdap adap = new BlockchainAdap(this, arrayList);

        listView.setAdapter(adap);
        adap.notifyDataSetChanged();

        Iterator<Block> it = Cache.b.iterator();
        while(it.hasNext()){
            Block b = (Block) it.next();
            arrayList.add((b));
        }

        //Toast.makeText(this, listView.getCount(), Toast.LENGTH_SHORT).show();
    }
}
