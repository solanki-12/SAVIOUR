package com.example.saviour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FirebaseUserDetails extends AppCompatActivity {


    RecyclerView recyclerView;

    MyAdapterUser myAdapterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_user_details);
        setTitle("");

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<UserDataModel> options =
                new FirebaseRecyclerOptions.Builder<UserDataModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Registered Users"),UserDataModel.class)
                        .build();

        myAdapterUser = new MyAdapterUser(options);
        recyclerView.setAdapter(myAdapterUser);

    }


    @Override
    protected void onStart() {
        super.onStart();
        myAdapterUser.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapterUser.stopListening();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        getMenuInflater().inflate(R.menu.searchmenu, menu);
//
//        MenuItem  item = menu.findItem(R.id.searchbar);
//
//        SearchView searchView = (SearchView)item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                processsearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                processsearch(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    private void processsearch(String s) {
//        FirebaseRecyclerOptions<UserDataModel> options =
//                new FirebaseRecyclerOptions.Builder<UserDataModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Registered Users").orderByChild("fullName").startAt(s).endAt(s+"\uf8ff"),UserDataModel.class)
//                        .build();
//
//        myAdapterUser = new MyAdapterUser(options);
//        myAdapterUser.startListening();
//        recyclerView.setAdapter(myAdapterUser);
//    }

}