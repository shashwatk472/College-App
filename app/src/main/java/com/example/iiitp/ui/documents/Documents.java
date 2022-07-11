package com.example.iiitp.ui.documents;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.iiitp.R;

public class Documents extends Fragment implements View.OnClickListener{

    private DocumentsViewModel mViewModel;
    final static int PICK_PDF_CODE = 2342;

    TextView mUploadTextView;
    TextView mViewUploads;

    public static Documents newInstance() {
        return new Documents();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.documents_fragment, container, false);

        mUploadTextView = view.findViewById(R.id.upload_new);
        mViewUploads = view.findViewById(R.id.view_uploads);

        mUploadTextView.setOnClickListener(this);
        mViewUploads.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DocumentsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id==R.id.upload_new){
            Intent intent = new Intent(getActivity(),UploadActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.view_uploads){
            Intent intent = new Intent(getActivity(),ViewUploadActivity.class);
            startActivity(intent);
        }
    }
}