package com.example.saviour;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {


    TextView fullname, location, services, mobile;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        fullname = (TextView)itemView.findViewById(R.id.ul_fullname);
        location = (TextView)itemView.findViewById(R.id.ul_loc);
        services = (TextView)itemView.findViewById(R.id.ul_serv);
        mobile = (TextView) itemView.findViewById(R.id.ul_mobile);
    }
}
