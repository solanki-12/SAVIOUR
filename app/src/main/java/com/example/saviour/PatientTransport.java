package com.example.saviour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Locale;

public class PatientTransport extends AppCompatActivity implements LocationListener{


    Button button_location;

    Button button;

    Dialog dialog;
    TextView textView_location;
    LocationManager locationManager;

    DatabaseReference userlocationDbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_transport);

        // Success Dialog
        dialog = new Dialog(PatientTransport.this);
        dialog.setContentView(R.layout.custom_dialog);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.icon_background));

        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        Button okay = dialog.findViewById(R.id.btn_okay);
        Button cancel = dialog.findViewById(R.id.btn_cancel);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PatientTransport.this,DashboardActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatientTransport.this, "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });



        userlocationDbRef = FirebaseDatabase.getInstance().getReference().child("Users Location");



        // Runtime Permission for get Access for location.
        if(ContextCompat.checkSelfPermission(PatientTransport.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PatientTransport.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }


        textView_location = findViewById(R.id.patienttrans_locationtxt);
        button_location = findViewById(R.id.button_patientTrans);

        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();

            }
        });
    }


    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, PatientTransport.this);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();

        try {
            // Geocoder class for refers to transforming street address or any other address into lat and long
            Geocoder geocoder = new Geocoder(PatientTransport.this, Locale.getDefault());
            // Address class helps in fetching the street address , locality , city, country, etc.
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);

            textView_location.setText(address);
            addUserAddress(address);
            dialog.show();


        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void addUserAddress(String address) {
        String useraddress = address;
        String AmbulanceType = "Patient Transport";
        UsersLocationAddress usersLocationAddress = new UsersLocationAddress(useraddress, AmbulanceType);
        userlocationDbRef.push().setValue(usersLocationAddress);
        Toast.makeText(PatientTransport.this,"Data Inserted",Toast.LENGTH_SHORT).show();

        //Sending data To another Activity
//        String useraddress = address;
//        Intent intent = new Intent(PatientTransport.this, AdminActivity.class);
//        intent.putExtra("keyAddress", useraddress);
//        startActivity(intent);
//        finish();
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }



}