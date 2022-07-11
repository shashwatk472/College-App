package com.example.iiitp.ui.faculty.utlities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iiitp.R;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder> {
    public FacultyData[] mdata;
    public FacultyAdapter(FacultyData[] dataSet){
        mdata = dataSet;
    }

    @Override
    public FacultyAdapter.FacultyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_layout,parent,false);
        return new FacultyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewHolder holder, int position) {
        holder.image.setImageResource(mdata[position].imageId);
        holder.name.setText(mdata[position].name);
        holder.email.setText(mdata[position].email);
        holder.qualification.setText(mdata[position].qualification);
        holder.position.setText(mdata[position].positiion);
        holder.phone.setText(mdata[position].phoneNumber);
    }

    @Override
    public int getItemCount() {
        return mdata.length;
    }

    public class FacultyViewHolder extends RecyclerView.ViewHolder{
        private final TextView name;
        private final TextView position;
        private final TextView qualification;
        private final TextView email;
        private final TextView phone;
        private final ImageView image;

        public FacultyViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.f_name);
            position = (TextView) view.findViewById(R.id.f_position);
            qualification = (TextView) view.findViewById(R.id.f_qualification);
            email = (TextView) view.findViewById(R.id.f_email);
            phone = (TextView) view.findViewById(R.id.f_phone);
            image = (ImageView) view.findViewById(R.id.f_image);
        }
    }
}
