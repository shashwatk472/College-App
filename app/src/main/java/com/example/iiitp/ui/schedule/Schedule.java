package com.example.iiitp.ui.schedule;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iiitp.R;
import com.example.iiitp.ui.scholarship.utilities.ScholarshipAdapter;
import com.example.iiitp.ui.scholarship.utilities.ScholarshipData;

public class Schedule extends Fragment implements ScheduleAdapter.onNoteListener {
    private RecyclerView recyclerView;
  //  private ScheduleViewModel mViewModel;
  private Scheduledata[] mdata;
  private ScheduleAdapter adapter;
    public static Schedule newInstance() {
        return new Schedule();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.schedule_fragment, container, false);
        mdata = new Scheduledata[]{
                new Scheduledata("First Year Schedule"),
                new Scheduledata("Second Year Schedule"),
                new Scheduledata("Third Year Schedule"),
                new Scheduledata("Final Year Schedule"),
                new Scheduledata("Mtech Year Schedule")
        };
        recyclerView = (RecyclerView)view.findViewById(R.id.schedule_recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

       adapter = new ScheduleAdapter(mdata,this);

        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
     //   mViewModel = new ViewModelProvider(this).get(ScheduleViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent=new Intent(getContext(), ItemActivity.class);
        getActivity().startActivity(intent);

    }
}