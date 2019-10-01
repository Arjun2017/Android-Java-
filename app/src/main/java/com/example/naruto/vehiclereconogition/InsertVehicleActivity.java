package com.example.naruto.vehiclereconogition;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InsertVehicleActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private Spinner vehicleSpinner;
    private EditText mRegistrationText;
    private EditText mModelNameText;
    private EditText mCcText;
    private EditText mMakeText;
    private EditText mImageText;
    private EditText mColorText;
    private EditText mFirstName;
    private EditText mLastname;
    private EditText mAddress;
    private EditText mContactNumber;
    private static final int EXISTING_VEHICLE_LOADER = 0;
    private Uri mCurrentVehicleUri;

    private int mVehicle = 0;
    private boolean mVehicleHasChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mVehicleHasChanged = true;
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_vehicle);

        Intent intent = getIntent();
         mCurrentVehicleUri = intent.getData();

        if (mCurrentVehicleUri == null){
            setTitle("Add a Vehicle");
            invalidateOptionsMenu();
        }else {
            setTitle(getString(R.string.editor_activity_title_edit_vehicle));

            getLoaderManager().initLoader(EXISTING_VEHICLE_LOADER, null, this);
        }


        mRegistrationText = (EditText) findViewById(R.id.edit_registration);
        mModelNameText = (EditText) findViewById(R.id.edit_model);
        mMakeText = (EditText) findViewById(R.id.edit_make);
        mColorText = (EditText) findViewById(R.id.edit_color);
        mImageText = findViewById(R.id.edit_image);
        mCcText = (EditText) findViewById(R.id.edit_cc);
        mFirstName = (EditText) findViewById(R.id.edit_first_name);
        mLastname = (EditText) findViewById(R.id.edit_last_name);
        mAddress = (EditText) findViewById(R.id.edit_address);
        mContactNumber = (EditText) findViewById(R.id.edit_contact_number);


        mRegistrationText.setOnTouchListener(mTouchListener);
        mModelNameText.setOnTouchListener(mTouchListener);
        mMakeText.setOnTouchListener(mTouchListener);
        mColorText.setOnTouchListener(mTouchListener);
        mImageText.setOnTouchListener(mTouchListener);
        mCcText.setOnTouchListener(mTouchListener);
        mFirstName.setOnTouchListener(mTouchListener);
        mLastname.setOnTouchListener(mTouchListener);
        mAddress.setOnTouchListener(mTouchListener);
        mContactNumber.setOnTouchListener(mTouchListener);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vehicleSpinner = (Spinner) findViewById(R.id.spinner_vehicle);
        setupSpinner();

    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_vehicle_option, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        vehicleSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        vehicleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Car")) {
                        mVehicle = VehicleContract.VehicleEntry.TYPE_CAR; // Male
                    } else if (selection.equals("Bike")) {
                        mVehicle = VehicleContract.VehicleEntry.TYPE_BIKE; // Female
                    } else if (selection.equals("Truck")) {
                        mVehicle = VehicleContract.VehicleEntry.TYPE_TRUCK; // Female
                    }
                    else {
                        mVehicle = VehicleContract.VehicleEntry.TYPE_BUS_; // Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mVehicle = 0; // Unknown
            }
        });
    }

    private void insertvehicle(){

        String registrationString = mRegistrationText.getText().toString().trim();
        String modelString = mModelNameText.getText().toString().trim();
        String makeString = mMakeText.getText().toString().trim();
        String colorString = mColorText.getText().toString().trim();
        String image = mImageText.getText().toString().trim();
        String ccString = mCcText.getText().toString().trim();
        String firstnameString = mFirstName.getText().toString().trim();
        String lastnameString = mLastname.getText().toString().trim();
        String addressString = mAddress.getText().toString().trim();
        String contactnumberString = mContactNumber.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(VehicleContract.VehicleEntry.VEHICLE_REGISTRATION, registrationString);
        values.put(VehicleContract.VehicleEntry.VEHICLE_MODEL, modelString);
        values.put(VehicleContract.VehicleEntry.VEHICLE_MAKE, makeString);
        values.put(VehicleContract.VehicleEntry.VEHICLE_COLOR, Integer.parseInt(colorString));
        values.put(VehicleContract.VehicleEntry.VEHICLE_IMAGE, Integer.parseInt(image));
        values.put(VehicleContract.VehicleEntry.VEHICLE_CC, Integer.parseInt(ccString));
        values.put(VehicleContract.VehicleEntry.VEHICLE_TYPE, mVehicle);
        values.put(VehicleContract.VehicleEntry.VEHICLE_FIRST_NAME, firstnameString);
        values.put(VehicleContract.VehicleEntry.VEHICLE_LAST_NAME, lastnameString);
        values.put(VehicleContract.VehicleEntry.VEHICLE_OWNER_ADDRESS, addressString);
        values.put(VehicleContract.VehicleEntry.VEHICLE_CONTACT_NUMBER, contactnumberString);

        Uri newUri = getContentResolver().insert(VehicleContract.VehicleEntry.CONTENT_URI, values);

        if (newUri == null){
            Toast.makeText(this, "Error with saving vehicle", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "vehicle saved", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new pet, hide the "Delete" menu item.
        if (mCurrentVehicleUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                insertvehicle();
                finish();
                return true;

            case R.id.action_delete:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
