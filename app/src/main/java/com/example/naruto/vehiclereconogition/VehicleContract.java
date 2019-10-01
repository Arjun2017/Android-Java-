package com.example.naruto.vehiclereconogition;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import java.security.PublicKey;

public final class VehicleContract {

    private VehicleContract(){}

    public final static String CONTENT_AUTHORITY = "com.example.naruto.vehiclereconogition";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_VEHICLES = "vehicle";


    public static  final class VehicleEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_VEHICLES);

        public final static String TABLE_NAME = "Vehicle";
        public final static String VEHICLE_ID = BaseColumns._ID;
        public final static String VEHICLE_TYPE = "vehicle";
        public final static String VEHICLE_REGISTRATION = "registration";
        public final static String VEHICLE_MODEL = "model";
        public final static String VEHICLE_MAKE = "brandname";
        public final static String VEHICLE_CC = "number";
        public final static String VEHICLE_COLOR = "colorname";
        public final static String VEHICLE_IMAGE = "image";
        public final static String VEHICLE_FIRST_NAME = "Raj";
        public final static String VEHICLE_LAST_NAME = "Shrestha";
        public final static String VEHICLE_OWNER_ADDRESS = "Kathmandu";
        public final static String  VEHICLE_CONTACT_NUMBER = "try";

        public static final int TYPE_TRUCK=0;
        public static final int TYPE_BUS_=1;
        public static final int TYPE_CAR=2;
        public static final int TYPE_BIKE=3;





    }
}
