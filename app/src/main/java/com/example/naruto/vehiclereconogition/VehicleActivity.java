package com.example.naruto.vehiclereconogition;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

public class VehicleActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private Menu menu;
    private static final int VEHICLE_LOADER = 0;
    VehicleCursorAdapter mCursorAdapter;
    private static final String TAG = "VehicleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VehicleActivity.this, InsertVehicleActivity.class);
                startActivity(intent);
            }
        });

        SwipeMenuListView vehicleListView = (SwipeMenuListView) findViewById(R.id.list);
        //RecyclerView vehicleListView = (RecyclerView) findViewById(R.id.recycle_view) ;

        View emptyView = findViewById(R.id.empty_view);
        vehicleListView.setEmptyView(emptyView);

        mCursorAdapter = new VehicleCursorAdapter(this, null);
        vehicleListView.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(VEHICLE_LOADER, null,  this);

        SwipeMenuCreator listCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                //create edit item
                SwipeMenuItem editItem = new SwipeMenuItem(getApplicationContext());
                editItem.setBackground(new ColorDrawable((Color.rgb(0xc9, 0xC9, 0xCE ))));

                //set item width
                editItem.setWidth(170);

                //set title
                editItem.setTitle("Edit");
                editItem.setTitleSize(18);
                editItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(editItem);

                //create delete item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(170);
                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);

                //create owner item
                SwipeMenuItem ownerItem = new SwipeMenuItem(
                        getApplicationContext());
                ownerItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                ownerItem.setWidth(170);

                //set title
                ownerItem.setTitle("Owner");
                ownerItem.setTitleSize(10);
                ownerItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(ownerItem);

            }
        };

        vehicleListView.setMenuCreator(listCreator);
        vehicleListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Intent intent = new Intent(VehicleActivity.this, InsertVehicleActivity.class);
                        Uri currentPetUri = ContentUris.withAppendedId(VehicleContract.VehicleEntry.CONTENT_URI, position);
                        intent.setData(currentPetUri);
                        startActivity(intent);
                        break;
                    case 1:
                        Log.d(TAG, "onMenuItemClicked: clicked item" + index );
                        break;

                    case 2:
                        Intent newintent = new Intent(VehicleActivity.this, OwnerInformationActivity.class);
                        startActivity(newintent);
                        break;
                       /* Intent newintent = new Intent(VehicleActivity.this, OwnerInformation.class);
                        startActivity(newintent);*/

                }


                // false : close the menu; true : not close the menu
                return false;
            }
        });




        String activity_label = getIntentValue();
        setTitle(activity_label + " Activity");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }










    private void insertVehicle(){
        ContentValues values = new ContentValues();
        values.put(VehicleContract.VehicleEntry.VEHICLE_REGISTRATION, "ABC123456789");
        values.put(VehicleContract.VehicleEntry.VEHICLE_MODEL, "DGD12356");
        values.put(VehicleContract.VehicleEntry.VEHICLE_TYPE, VehicleContract.VehicleEntry.TYPE_BUS_);
        values.put(VehicleContract.VehicleEntry.VEHICLE_MAKE, "TOYATA");
        values.put(VehicleContract.VehicleEntry.VEHICLE_IMAGE, 1335);
        values.put(VehicleContract.VehicleEntry.VEHICLE_COLOR, 12345);
        values.put(VehicleContract.VehicleEntry.VEHICLE_CC, 250 );
        values.put(VehicleContract.VehicleEntry.VEHICLE_FIRST_NAME, "sed");
        values.put(VehicleContract.VehicleEntry.VEHICLE_LAST_NAME, "DES");
        values.put(VehicleContract.VehicleEntry.VEHICLE_OWNER_ADDRESS, "FES");
        values.put(VehicleContract.VehicleEntry.VEHICLE_CONTACT_NUMBER, 12345);

        Uri newUri = getContentResolver().insert(VehicleContract.VehicleEntry.CONTENT_URI, values);

    }
    private void deletAllVehicle(){
        int rowsDeleted = getContentResolver().delete(VehicleContract.VehicleEntry.CONTENT_URI, null, null);
        Log.v("vehicleActivity", rowsDeleted + " rows deleted from Vehicle database");

    }


    public String getIntentValue(){
        Intent id = getIntent();
        String label = id.getStringExtra("label");
        return label;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_vehicle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_dummydata:
                insertVehicle();
                return true;

            case R.id.action_deletevehicle:
                deletAllVehicle();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                VehicleContract.VehicleEntry.VEHICLE_ID,
                VehicleContract.VehicleEntry.VEHICLE_REGISTRATION,
                VehicleContract.VehicleEntry.VEHICLE_MODEL,
                VehicleContract.VehicleEntry.VEHICLE_MAKE
        };


        return new CursorLoader(this, VehicleContract.VehicleEntry.CONTENT_URI,
                projection, null, null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
