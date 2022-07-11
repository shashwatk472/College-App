package com.example.iiitp.ui.faculty;

import androidx.lifecycle.ViewModelProvider;

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
import com.example.iiitp.ui.faculty.utlities.FacultyAdapter;
import com.example.iiitp.ui.faculty.utlities.FacultyData;

public class Faculty extends Fragment {

    public static Faculty newInstance() {
        return new Faculty();
    }

    private FacultyData[] mData;
    private FacultyAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faculty_fragment, container, false);
        mData = new FacultyData[]{
            new FacultyData("Dr. Ritu Tiwari","Designation Professor",
                    "Educational Qualification Ph.D. (NIT Raipur)","tiwariritu2@gmail.com",
                    "+91 6263302955",R.drawable.ritu_mam),
                new FacultyData("Dr. Apoorva Mishra","Designation Professor",
                        "Educational Qualification Ph.D.","apoorvamishra@gmail.com",
                        "+91 6263302955",R.drawable.apoo),
                new FacultyData("Dr. Rahul Dixit","Designation Professor",
                        "Educational Qualification Ph.D. (CSE) from NIT Rourkela","rahul2012ism@gmail.com",
                        "+91 9438867961",R.drawable.rahul),
                new FacultyData("Dr. Nagendra Kushwaha","Designation Professor and HOD",
                        "Educational Qualification Ph.D. ","nagendrakushwaha@gmail.com",
                        "+91 6263302955",R.drawable.nagoo),
                new FacultyData("Dr. Anagha Uday Khiste","Designation Professor",
                        "Educational Qualification Ph.D. ","anaghakhiste@gmail.com",
                        "+91 6263302955",R.drawable.angoo)
        };

        recyclerView = view.findViewById(R.id.faculty_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new FacultyAdapter(mData);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}