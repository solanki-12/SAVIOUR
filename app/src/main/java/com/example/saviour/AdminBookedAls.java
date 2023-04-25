package com.example.saviour;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminBookedAls extends AppCompatActivity {


    RecyclerView recyclerView;
    MyAdapterAdminAls myAdapterAdminAls;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booked_als);
        setTitle("");



        recyclerView = (RecyclerView)findViewById(R.id.adminBookedAls_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<BookedAlsModel> options2 =
                new FirebaseRecyclerOptions.Builder<BookedAlsModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users Location").orderByChild("ambulanceType").equalTo("Advance Life Support"),BookedAlsModel.class)
                        .build();


        myAdapterAdminAls = new MyAdapterAdminAls(options2);
        recyclerView.setAdapter(myAdapterAdminAls);

    }



    @Override
    protected void onStart() {
        super.onStart();
        myAdapterAdminAls.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapterAdminAls.stopListening();
    }
}