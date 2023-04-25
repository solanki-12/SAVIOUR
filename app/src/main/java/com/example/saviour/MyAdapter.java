package com.example.saviour;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements Filterable {


    ArrayList<User> data;
    ArrayList<User> backup;

    public MyAdapter(ArrayList<User> data)
    {
        this.data = data;
        backup = new ArrayList<>(data);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.singlerow,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.fullname.setText(data.get(position).getFullName());
        holder.location.setText(data.get(position).getLocation());
        holder.services.setText(data.get(position).getServices());
        holder.mobile.setText(data.get(position).getMobile());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        // Background Thread
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<User> filteredata = new ArrayList<>();

            if(keyword.toString().isEmpty())
            {
                    filteredata.addAll(backup);
            }else {
                for(User obj : backup)
                {
                    if(obj.getLocation().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                    {
                        filteredata.add(obj);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredata;
            return results;
        }

        @Override // Main UI thread
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            data.clear();
            data.addAll((ArrayList<User>)results.values);
            notifyDataSetChanged();
        }

    };

}
