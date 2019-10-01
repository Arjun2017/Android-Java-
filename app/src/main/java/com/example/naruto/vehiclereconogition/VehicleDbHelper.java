package com.example.naruto.vehiclereconogition;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VehicleDbHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "Vehicle.db";

    private static final int DATABASE_VERSION = 1;

    public VehicleDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_VEHICLE_TABLE = "CREATE TABLE " + VehicleContract.VehicleEntry.TABLE_NAME + "("
                + VehicleContract.VehicleEntry.VEHICLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + VehicleContract.VehicleEntry.VEHICLE_TYPE + " TEXT NOT NULL, "
                + VehicleContract.VehicleEntry.VEHICLE_REGISTRATION + " TEXT NOT NULL, "
                + VehicleContract.VehicleEntry.VEHICLE_MODEL + " TEXT NOT NULL, "
                + VehicleContract.VehicleEntry.VEHICLE_MAKE + " TEXT NOT NULL, "
                + VehicleContract.VehicleEntry.VEHICLE_IMAGE + " INTEGER NOT NULL, "
                + VehicleContract.VehicleEntry.VEHICLE_COLOR + " INTEGER NOT NULL, "
                + VehicleContract.VehicleEntry.VEHICLE_FIRST_NAME + "TEXT NOT NULL, "
                + VehicleContract.VehicleEntry.VEHICLE_CC + " INTEGER NOT NULL, "
                + VehicleContract.VehicleEntry.VEHICLE_LAST_NAME + "TEXT NOT NULL, "
                + VehicleContract.VehicleEntry.VEHICLE_OWNER_ADDRESS + "TEXT NOT NULL,"
                + VehicleContract.VehicleEntry.VEHICLE_CONTACT_NUMBER + "INTERGER NOT NULL);";

            db.execSQL(SQL_CREATE_VEHICLE_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
