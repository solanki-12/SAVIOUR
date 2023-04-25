package com.example.saviour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    GoogleSignInOptions gso;
    private FirebaseAuth authProfile;
    GoogleSignInClient gsc;




    ImageView getImageView_chat, getImageView_als, getImageView_bls, getImageView_notifi,
     getImageView_map, getImageView_ambulance;

//    ImageView imageView;


    @SuppressLint("UseSupportActionBar")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);




        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();

//

        /*-------------------- HOOKS -------------*/
        drawerLayout = findViewById(R.id.dash_drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        getImageView_notifi = findViewById(R.id.dash_notification);
        getImageView_map = findViewById(R.id.dash_search);
        getImageView_bls = findViewById(R.id.dash_bls);
        getImageView_als = findViewById(R.id.dash_als);
        getImageView_chat = findViewById(R.id.dash_chat);
        getImageView_ambulance = findViewById(R.id.dash_amb);
        getImageView_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        getImageView_als.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ALSActivity.class);
                startActivity(intent);
            }
        });

        getImageView_bls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, BLSActivity.class);
                startActivity(intent);

            }
        });

        getImageView_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, PatientTransport.class);
                startActivity(intent);
            }
        });



//        signout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signOut();
//                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
//                finish();
//            }
//        });







        /*-------------------- Tool Bar -------------*/
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        /*-------------------- Navigation Drawer Menu -------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.nav_profile)
        {
            Intent intent = new Intent(DashboardActivity.this, UserProfileActivity.class);
            startActivity(intent);
        }else if(id == R.id.nav_logout)
        {
            authProfile.signOut();
            gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    finish();
                    Intent intent = new Intent(DashboardActivity.this,MainActivity.class);
//                    startActivity(new Intent(DashboardActivity.this, MainActivity.class));
                    //clear stack to prevent user coming back to home activity on pressing back button after logging out
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish(); // close home activity
                    Toast.makeText(DashboardActivity.this, "Logged Out", Toast.LENGTH_LONG).show();
                }
            });
        }else  if(id == R.id.nav_refresh) {
            //refresh Activity
            startActivity(getIntent());
            finish();
            overridePendingTransition(0, 0);
        }else if(id == R.id.nav_share){
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello User, Welcom to Saviour App you can download it from play store !!");
            shareIntent.setType("text/plain");

            if(shareIntent.resolveActivity(getPackageManager()) != null){
                startActivity(shareIntent);
            }
        }else if(id == R.id.nav_rate){
            Toast.makeText(DashboardActivity.this, "Admin Portal", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(DashboardActivity.this, AdminDashboard.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(DashboardActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }


        return super.onOptionsItemSelected(item);
    }
}