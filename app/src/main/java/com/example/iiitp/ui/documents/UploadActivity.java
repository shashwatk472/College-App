package com.example.iiitp.ui.documents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iiitp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener{

    final static int PICK_PDF_CODE = 2342;
    TextView mFilepathTextView;
    TextView mFileNameTextView;
    TextView mUploadButton;
    TextView mFileStatus;
    TextView mChooseFileButton;
    EditText mFileNameEditText;
    TextView mFilePath;
    ProgressBar progressBar;
    private Uri filePath;

    StorageReference mStorageReference;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        mChooseFileButton = findViewById(R.id.choose_file);
        mFilepathTextView = findViewById(R.id.file_path_text_view);
        mFileNameTextView = findViewById(R.id.file_name_text_view);
        mFileStatus = findViewById(R.id.file_status);
        mUploadButton = findViewById(R.id.upload_button);
        mFilePath = findViewById(R.id.file_path);
        mFileNameEditText = findViewById(R.id.file_name_edit_text);
        progressBar = findViewById(R.id.progressBar);

        mChooseFileButton.setOnClickListener(this);
        mUploadButton.setOnClickListener(this);

        mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);
    }

    private void getPDF() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            return;
        }

        //creating an intent for file chooser
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //when the user choses the file
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                filePath = data.getData();
                mChooseFileButton.setVisibility(View.INVISIBLE);
                mUploadButton.setVisibility(View.VISIBLE);
                mFileNameEditText.setVisibility(View.VISIBLE);
                mFileNameTextView.setVisibility(View.VISIBLE);
                mFilepathTextView.setVisibility(View.VISIBLE);
                mFilePath.setVisibility(View.VISIBLE);
                mFilePath.setText(data.getData().toString());
                mFileStatus.setVisibility(View.INVISIBLE);

            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadFile(Uri data) {
        progressBar.setVisibility(View.VISIBLE);
        StorageReference sRef = mStorageReference.child(Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + ".pdf");
        sRef.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressBar.setVisibility(View.GONE);
                        mFileStatus.setVisibility(View.VISIBLE);
                        mUploadButton.setVisibility(View.INVISIBLE);
                        mFileNameEditText.setVisibility(View.INVISIBLE);
                        mFileNameTextView.setVisibility(View.INVISIBLE);
                        mFilepathTextView.setVisibility(View.INVISIBLE);
                        mFilePath.setVisibility(View.INVISIBLE);
                        mFileStatus.setTextSize(24);
                        mFileStatus.setText("File Uploaded Successfully");

                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uri.isComplete());
                        Uri url = uri.getResult();

                        Upload upload = new Upload(mFileNameEditText.getText().toString(),url.toString());
                        mDatabaseReference.child(mDatabaseReference.push().getKey()).setValue(upload);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        mFileStatus.setText((int) progress + "% Uploading...");
                    }
                });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id==R.id.choose_file){
            getPDF();
        }
        else if(id==R.id.upload_button){
            uploadFile(filePath);
        }
    }
}