package com.example.naruto.vehiclereconogition;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class VehicleCursorAdapter extends CursorAdapter {
    public VehicleCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView registrationTextView = (TextView) view.findViewById(R.id.name);
        TextView modelTextView = (TextView) view.findViewById(R.id.summary);
        TextView makeTextView = (TextView) view.findViewById(R.id.make);

        int registrationColumnIndex = cursor.getColumnIndex(VehicleContract.VehicleEntry.VEHICLE_REGISTRATION);
        int modelColumnIndex = cursor.getColumnIndex(VehicleContract.VehicleEntry.VEHICLE_MODEL);
        int makeColumnIndex = cursor.getColumnIndex(VehicleContract.VehicleEntry.VEHICLE_MAKE);

        String regname = cursor.getString(registrationColumnIndex);
        String modname = cursor.getString(modelColumnIndex);
        String makename = cursor.getString(makeColumnIndex);

        registrationTextView.setText(regname);
        modelTextView.setText(modname);
        makeTextView.setText(makename);

    }
}
