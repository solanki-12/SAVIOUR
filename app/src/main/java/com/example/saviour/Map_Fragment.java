package com.example.saviour;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map_Fragment extends Fragment {





    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map_, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MY_MAP);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {

//                        LatLng ghaziabad = new LatLng(28.6692, 28.6692);
//                        mMap.addMarker(new MarkerOptions().position(ghaziabad).title("Ghaziabad"));
//                        mMap.moveCamera(CameraUpdateFactory.newLatLng(ghaziabad));
//                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ghaziabad, 16f));

                        //Add circle

                        LatLng ghaziabad = new LatLng(28.6692, 28.6692);
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(ghaziabad).title("ghaziabad");
//                        markerOptions.title(latLng.latitude+ " Anuj " + latLng.longitude);

                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ghaziabad));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ghaziabad, 16f));
                        googleMap.addMarker(markerOptions);
                        googleMap.clear();


                        //Add circle
                        googleMap.addCircle(new CircleOptions()
                                .center(ghaziabad)
                                .radius(1000)
                                .fillColor(android.R.color.holo_blue_light)
                                .strokeColor(android.R.color.darker_gray));

                    }
                });
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}