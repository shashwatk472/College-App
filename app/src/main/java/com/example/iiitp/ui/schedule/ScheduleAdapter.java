package com.example.iiitp.ui.schedule;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.iiitp.R;

import org.jetbrains.annotations.NotNull;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>{



    private onNoteListener monNoteListener;
    /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public static class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

            private TextView textView;
            onNoteListener OnNoteListener;

            public ViewHolder(View view,onNoteListener OnNoteListener) {
                super(view);

                // Define click listener for the ViewHolder's View

                textView = (TextView) view.findViewById(R.id.schedule_textView);
                this.OnNoteListener=OnNoteListener;
                //view.setOnClickListener(this);
                view.setOnClickListener(this);
            }
        @Override
        public void onClick(View v) {
        OnNoteListener.onNoteClick(getAdapterPosition());
        }
            public TextView getTextView() {
                return textView;
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
       public Scheduledata[] mdata;
        public ScheduleAdapter(Scheduledata[] dataSet,onNoteListener OnNoteListener) {
            mdata = dataSet;
            this.monNoteListener=OnNoteListener;
        }

        // Create new views (invoked by the layout manager)
        @NotNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.schedule_layout, viewGroup, false);

            return new ViewHolder(view,monNoteListener);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.getTextView().setText(mdata[position].name);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mdata.length;
        }
        public interface onNoteListener{
            void onNoteClick(int position);
        }

    }


