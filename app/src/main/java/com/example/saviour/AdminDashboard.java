package com.example.saviour;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class AdminDashboard extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{




    DrawerLayout adminDrawerLayout;
    NavigationView adminNavigationView;
    Toolbar adminToolbar;

    ImageView getImageView_user, getImageView_als, getImageView_phone, getImageView_bls,
    getImageView_notif, getImageView_ambu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);


        adminDrawerLayout = findViewById(R.id.dash_admin_drawer_layout);
        adminNavigationView = findViewById(R.id.nav_admin_view);
        adminToolbar = findViewById(R.id.toolbarAdmin);


        /*-------------------- Tool Bar -------------*/
        setSupportActionBar(adminToolbar);
        getSupportActionBar().setTitle("Admin");

        /*-------------------- Navigation Drawer Menu -------------*/
        adminNavigationView.bringToFront();
        ActionBarDrawerToggle toogleadmin = new ActionBarDrawerToggle(this,adminDrawerLayout,adminToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        adminDrawerLayout.addDrawerListener(toogleadmin);
        toogleadmin.syncState();

        adminNavigationView.setNavigationItemSelectedListener(this);


        /*         HOOKS       */
        getImageView_user = findViewById(R.id.dash_admin);
        getImageView_als = findViewById(R.id.dash_als_admin);
        getImageView_phone = findViewById(R.id.dash_phone_admin);
        getImageView_bls = findViewById(R.id.dash_bls_admin);
        getImageView_notif = findViewById(R.id.dash_notification_admin);
        getImageView_ambu = findViewById(R.id.dash_amb_admin);


        getImageView_als.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboard.this, AdminBookedAls.class);
                startActivity(intent);
            }
        });


        getImageView_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboard.this, userlist.class);
                startActivity(intent);
            }
        });

        getImageView_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboard.this, FirebaseUserDetails.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

}