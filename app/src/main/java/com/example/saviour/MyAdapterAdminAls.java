package com.example.saviour;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapterAdminAls extends FirebaseRecyclerAdapter<BookedAlsModel,MyAdapterAdminAls.myviewholderAdminAls>
{


    public MyAdapterAdminAls(@NonNull FirebaseRecyclerOptions<BookedAlsModel> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholderAdminAls holder, int position, @NonNull BookedAlsModel model)
    {

            holder.fullname.setText(model.getFullName());
            holder.mobile.setText(model.getMobile());

            holder.ambutype.setText(model.getAmbulanceType());
            holder.location.setText(model.getUseraddress());

//        Glide.with(holder.img1.getContext()).load(model.getPurl()).into(holder.img1);


    }


    @NonNull
    @Override
    public myviewholderAdminAls onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookedalsrow,parent,false);
        return new MyAdapterAdminAls.myviewholderAdminAls(view);
    }



    public class myviewholderAdminAls extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView fullname, mobile, ambutype, location;



        public myviewholderAdminAls(@NonNull View itemView)
        {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.imgals);
            fullname = (TextView) itemView.findViewById(R.id.tx_fullnameals);
            mobile = (TextView) itemView.findViewById(R.id.tx_mobileals);
            ambutype = (TextView) itemView.findViewById(R.id.tx_ambtypeals);
            location = (TextView) itemView.findViewById(R.id.tx_locationals);
            location.setPaintFlags(location.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        }

    }
}
