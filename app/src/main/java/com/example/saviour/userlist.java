package com.example.saviour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userlist extends AppCompatActivity {



    RecyclerView rcv;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        rcv = (RecyclerView)findViewById(R.id.recview);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(dataqueue());
        rcv.setAdapter(adapter);

    }

    public ArrayList<User> dataqueue()
    {
        ArrayList<User> holder = new ArrayList<>();
        User ob1 = new User();
        ob1.setFullName("Taynaam Ambulance Services (24*7)");
        ob1.setLocation("Taaynam Ambulance Services, Raj Nagar Extension, Ghaziabad, Uttar Pradesh 201017");
        ob1.setServices("ALS/ BLS/ PATIENT TRANSPORT");
        ob1.setMobile("08700185498");
        holder.add(ob1);

        User ob2 = new User();
        ob2.setFullName("Rapid Ambulance Services (24*7)");
        ob2.setLocation("Rapid Ambulance Services, Shop no. 563G, Nyay Khand 2, Indirapuram, Ghaziabad, Uttar Pradesh 201014");
        ob2.setServices("BLS/ PATIENT TRANSPORT");
        ob2.setMobile("8787928382");
        holder.add(ob2);

        User ob3 = new User();
        ob3.setFullName("Golder Hour Rescue-Internal Air (24*7)");
        ob3.setLocation("Golden Hour Rescue - International Air Ambulane and ECMO Services, 647, SECTOR 5, VAISHALI, GHAZIABAD NCR, Uttar Pradesh 201010");
        ob3.setServices("Air/ ALS/ BLS/ PATIENT TRANSPORT");
        ob3.setMobile("09599418192");
        holder.add(ob3);

        User ob4 = new User();
        ob4.setFullName("Jyoti Ambulance Services (24*7)");
        ob4.setLocation("Max Super Speciality Hospital 2 Cancer Hospital, Vaishali, near Max Hospital, Sector-1, Vasundhara, Ghaziabad, Uttar Pradesh 201012");
        ob4.setServices("Air/ ALS/ BLS/ PATIENT TRANSPORT");
        ob4.setMobile("---");
        holder.add(ob4);

        User ob5 = new User();
        ob5.setFullName("Deepak Ambulance Service (24*7)");
        ob5.setLocation("Sector 8, Vasundhara, near Vasundhara Hospital, Ghaziabad, Uttar Pradesh 201012");
        ob5.setServices("ALS/ BLS/ PATIENT TRANSPORT");
        ob5.setMobile("088265 67977");
        holder.add(ob5);

        User ob6 = new User();
        ob6.setFullName("Om Ambulance Service (24*7)");
        ob6.setLocation("Purana Bus adda, Ghaziabad, Uttar Pradesh 201001");
        ob6.setServices("ALS/ BLS/ PATIENT TRANSPORT");
        ob6.setMobile("090126 58946");
        holder.add(ob6);


        User ob7 = new User();
        ob7.setFullName("Lovekush Ambulance Service (24*7)");
        ob7.setLocation("Gali no- 1, Navneet Vihar Gali Number 1, Khora Colony, Ghaziabad, Uttar Pradesh 201309");
        ob7.setServices("ALS/ BLS/ PATIENT TRANSPORT");
        ob7.setMobile("---");
        holder.add(ob7);


        return holder;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item = menu.findItem(R.id.search_menu);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}