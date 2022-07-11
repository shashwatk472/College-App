package com.example.iiitp.ui.scholarship.utilities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iiitp.R;
import com.example.iiitp.ui.scholarship.Scholarship;

public class ScholarshipAdapter extends RecyclerView.Adapter<ScholarshipAdapter.ScholarshipViewHolder> {

    public ScholarshipData[] mdata;
    public ScholarshipAdapter(ScholarshipData[] dataSet,MyClickListener listener) {
        mdata = dataSet;
        myClickListener = listener;
    }
     final private MyClickListener myClickListener;

    public interface MyClickListener{
        void myOnClick(int position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ScholarshipViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.scholarship_layout, viewGroup, false);

        return new ScholarshipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScholarshipViewHolder holder, int position) {
        holder.textView.setText(mdata[position].name);
        holder.imageView.setImageResource(mdata[position].imageId);
    }

    public int getItemCount() {
        return mdata.length;
    }

    public class ScholarshipViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView textView;
        private final ImageView imageView;

        public ScholarshipViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.scholarship_textview);
            imageView = (ImageView) view.findViewById(R.id.scholarship_imageview);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            myClickListener.myOnClick(position);
        }
    }
}
