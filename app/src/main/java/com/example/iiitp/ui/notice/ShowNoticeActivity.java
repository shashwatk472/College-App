package com.example.iiitp.ui.notice;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iiitp.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowNoticeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    private RecyclerView.Adapter adapter;


    private ProgressDialog progressDialog;


    public List<NoticeUpload> uploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_notice_activity);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        progressDialog = new ProgressDialog(this);

        uploads = new ArrayList<NoticeUpload>();


        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference(NoticeConstants.DATABASE_PATH_UPLOADS);


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                progressDialog.dismiss();


                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    NoticeUpload upload;
                    upload = postSnapshot.getValue(NoticeUpload.class);
                    uploads.add(upload);
                }

                adapter = new NoticeAdapter(getApplicationContext(), uploads);


                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });

    }
}
