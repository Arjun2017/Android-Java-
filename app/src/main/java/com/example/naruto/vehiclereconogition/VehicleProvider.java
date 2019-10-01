package com.example.naruto.vehiclereconogition;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class VehicleProvider extends ContentProvider {

    private static final int VEHICLES = 100;
    private static final int VEHICLE_NUMBER= 101;
    private static final int VEHICLE_MAKE = 103;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(VehicleContract.CONTENT_AUTHORITY, VehicleContract.PATH_VEHICLES, VEHICLES);

        sUriMatcher.addURI(VehicleContract.CONTENT_AUTHORITY, VehicleContract.PATH_VEHICLES + "/#", VEHICLE_NUMBER);
        sUriMatcher.addURI(VehicleContract.CONTENT_AUTHORITY, VehicleContract.PATH_VEHICLES + "/*", VEHICLE_MAKE);


    }

    public static final String LOG_TAG = VehicleProvider.class.getSimpleName();
    private VehicleDbHelper mDbHelper;
    @Override
    public boolean onCreate() {
        mDbHelper = new VehicleDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor = null;

        int match = sUriMatcher.match(uri);
        switch (match){
            case VEHICLES:
                cursor = database.query(VehicleContract.VehicleEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder );
                break;

            case VEHICLE_NUMBER:
                selection = VehicleContract.VehicleEntry.VEHICLE_ID + "=?";

                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri))
                };

                cursor = database.query(VehicleContract.VehicleEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;



                default:
                    throw new IllegalArgumentException("cannot query unknown URI" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case VEHICLES:
                return insertVehicle(uri, contentValues);
                default:
                    throw new IllegalArgumentException("Insertion is not support for " + uri);
        }

    }

    private Uri insertVehicle(Uri uri, ContentValues values){

        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        long id = database.insert(VehicleContract.VehicleEntry.TABLE_NAME, null, values );
        if (id == -1){
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match){
            case VEHICLES:
                rowsDeleted = database.delete(VehicleContract.VehicleEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case VEHICLE_NUMBER:
                selection = VehicleContract.VehicleEntry.VEHICLE_ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(VehicleContract.VehicleEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows deleted
        return rowsDeleted;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case VEHICLES:
                return updatePet(uri, contentValues, selection, selectionArgs);

            case VEHICLE_NUMBER:
                selection = VehicleContract.VehicleEntry.VEHICLE_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePet(uri, contentValues, selection, selectionArgs);

            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updatePet(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int rowUpdated = database.update(VehicleContract.VehicleEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowUpdated != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }


}
