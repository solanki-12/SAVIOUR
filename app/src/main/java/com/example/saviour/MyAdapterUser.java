package com.example.saviour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapterUser extends FirebaseRecyclerAdapter<UserDataModel,MyAdapterUser.myviewholder>
{

    public MyAdapterUser(@NonNull FirebaseRecyclerOptions<UserDataModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull UserDataModel model)
    {
       holder.fullname.setText(model.getFullName());
       holder.gender.setText(model.getGender());
       holder.mobile.setText(model.getMobile());
//        Glide.with(holder.img1.getContext()).load(model.getPurl()).into(holder.img1);

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagesinglerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {

        CircleImageView img1;
        TextView fullname, gender, mobile;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img1 = (CircleImageView) itemView.findViewById(R.id.img1);
            fullname = (TextView) itemView.findViewById(R.id.tx_fullname);
            gender = (TextView) itemView.findViewById(R.id.tx_gender);
            mobile = (TextView) itemView.findViewById(R.id.tx_mobile);

        }
    }

}
