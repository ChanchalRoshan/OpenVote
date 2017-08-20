package com.hackathon.chanchalroshan.openvote;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by amitroshan on 17/08/17.
 */

public class BlockchainAdap extends ArrayAdapter<Block> {


    public BlockchainAdap(Context context, ArrayList<Block> listchain) {
        super(context, 0, listchain);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Block b = getItem(position);

        if( convertView == null ){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.block_element, parent, false);
        }

        TextView hash_field = (TextView) convertView.findViewById(R.id.hash_field);
        TextView vote_field = (TextView) convertView.findViewById(R.id.vote_field);

        hash_field.setText(b.getHash());
        vote_field.setText(b.getVote());

        return convertView;
    }
}
