 package com.example.iiitp.ui.scholarship;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iiitp.R;
import com.example.iiitp.ui.scholarship.utilities.ScholarshipAdapter;
import com.example.iiitp.ui.scholarship.utilities.ScholarshipData;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class Scholarship extends Fragment implements ScholarshipAdapter.MyClickListener{

//    private ScholarshipViewModel mViewModel;
    private ScholarshipData[] mdata;
    private ScholarshipAdapter adapter;
    private RecyclerView recyclerView;

    public static Scholarship newInstance() {
        return new Scholarship();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.scholarship_fragment, container, false);

        mdata = new ScholarshipData[]{
                new ScholarshipData("Chief Minister Medhavi Vidhyarthi Yojna",R.drawable.mmvy,"MP"),
                new ScholarshipData("National Scholarship Portal",R.drawable.nsp,"NSP"),
                new ScholarshipData("Government of Maharashtra State Scholarship",R.drawable.maharashtra,"MAH"),
                new ScholarshipData("Uttar Pradesh State Scholarship",R.drawable.up,"UP")
        };
        recyclerView = view.findViewById(R.id.scholarship_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new ScholarshipAdapter(mdata,this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(ScholarshipViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void myOnClick(int position) {
        String url="";
        String id = mdata[position].id;
        if(id=="MP"){
            url = "http://scholarshipportal.mp.nic.in/MedhaviChhatra/Medhavi_New/Default.aspx";
        }
        else if(id=="UP"){
            url = "https://scholarship.up.gov.in/";
        }
        else if(id=="MAH"){
            url = "https://mahadbtmahait.gov.in/";
        }
        else if(id=="NSP"){
            url="https://scholarships.gov.in/";
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        getActivity().startActivity(i);
    }
}