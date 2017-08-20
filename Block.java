package com.hackathon.chanchalroshan.openvote;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

/**
 * Created by amitroshan on 17/08/17.
 */

public class Block {

    public String timestamp, vote, hash, previousHash;

    Block( String timestamp, String vote, String previousHash ){
        this.timestamp = timestamp;
        this.vote = vote;
        this.hash = calculateHash();
        this.previousHash = previousHash;
    }

    public String calculateHash(){

        final HashCode temp_hash = Hashing.sha1().hashString(
                timestamp + vote + Cache.private_key
                , Charset.defaultCharset());

        return String.valueOf(temp_hash);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getVote() {
        return vote;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
}
