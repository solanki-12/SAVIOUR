package com.example.saviour;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.example.saviour.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;




 public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{

     FloatingActionButton btn;

    GoogleMap mGoogleMap;

    boolean isPermissionGranted;

    Location currentLocation;

    GoogleMap mMap;

    private ActivityMapsBinding binding;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);



         checkMyPermission();

        if(isPermissionGranted)
        {
            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.container_fm);
            supportMapFragment.getMapAsync(this);
        }

    }

     private void gotoLocation(double latitude, double longitude) {

         LatLng LatLng = new LatLng(latitude,longitude);
         CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng, 18);
         mGoogleMap.moveCamera(cameraUpdate);
         mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

         //Add circle
//         mGoogleMap.addCircle(new CircleOptions()
//                 .center(LatLng)
//                 .radius(1000)
//                 .fillColor(android.R.color.holo_blue_light)
//                 .strokeColor(android.R.color.darker_gray));
     }


     private void checkMyPermission() {

        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText(MapsActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                isPermissionGranted = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.cancelPermissionRequest();
            }
        }).check();
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {


     mGoogleMap = googleMap;
     mGoogleMap.setMyLocationEnabled(true);


        mMap = googleMap;
//
//        // Add a marker ghaziabad and move the camera
        LatLng ghaziabad = new LatLng(28.6692, 77.4538);
        mMap.addMarker(new MarkerOptions().position(ghaziabad).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ghaziabad));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ghaziabad, 16f));
//







    }


 }


