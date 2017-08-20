package com.hackathon.chanchalroshan.openvote;

import java.util.ArrayList;

public class Cache {

    public static String public_key = "";
    public static String private_key = "";
    public static String aadhar_number = "Aadhar Number";
    public static ArrayList<Block> b = new ArrayList<>();
    public static int voted = 0;

    public static void setKeys( String a, String b ){
        public_key = a;
        private_key = b;
    }

    public static String getPublic_key(){
        return public_key;
    }

    public static void setAadhar_number(String aadhar_number) {
        Cache.aadhar_number = aadhar_number;
    }

    public static String getAadhar_number() {
        return aadhar_number;
    }

    public  static String getPrivate_key(){
        return private_key;
    }

}
