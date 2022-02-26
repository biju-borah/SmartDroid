package com.example.smartdroid.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartdroid.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AddActivity extends AppCompatActivity {

    private Button btnSelect, btnUpload, addIngredient, addTag;

    private ImageView imageView;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 22;

    EditText inpIngredient, inpTag;

    String uuid;

    LinearLayout llIngredient, llTag;

    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnSelect = findViewById(R.id.btnChoose);
        btnUpload = findViewById(R.id.btnUpload);
        imageView = findViewById(R.id.imgView);
        addIngredient = findViewById(R.id.addIngredient);
        inpIngredient = findViewById(R.id.inpIngredient);
        inpTag = findViewById(R.id.inpTag);
        addTag = findViewById(R.id.addTag);

        llIngredient = findViewById(R.id.llIngredient);
        llTag = findViewById(R.id.llTag);


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        uuid = UUID.randomUUID().toString();

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addViewIngredient();
            }
        });

        addTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addViewTag();
            }
        });
    }

    private void  addViewIngredient(){
        if(inpIngredient.getText().toString().isEmpty()){
            Toast.makeText(AddActivity.this, "Please add ingredient.. !", Toast.LENGTH_SHORT).show();
            return;
        }
        TextView gb = new TextView(this);
        TextView textView = new TextView(this);
        textView.setBackgroundResource(R.drawable.tag);
        textView.setPadding(15,5,15,5);

        gb.setWidth(5);
        textView.setText(inpIngredient.getText().toString());
        llIngredient.addView(gb);
        llIngredient.addView(textView);
        inpIngredient.setText("");

    }

    private void  addViewTag(){
        if(inpTag.getText().toString().isEmpty()){
            Toast.makeText(AddActivity.this, "Please add Tag.. !", Toast.LENGTH_SHORT).show();
            return;
        }
        TextView gb = new TextView(this);
        TextView textView = new TextView(this);
        textView.setBackgroundResource(R.drawable.tag);
        textView.setPadding(15,5,15,5);

        gb.setWidth(5);
        textView.setText(inpTag.getText().toString());
        llTag.addView(gb);
        llTag.addView(textView);
        inpTag.setText("");

    }

    private void SelectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            filePath = data.getData();
            try {

                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // UploadImage method
    private void uploadImage() {
        if (filePath != null) {

            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + uuid);
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(AddActivity.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();

                                    addFirestore();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            progressDialog.dismiss();
                            Toast
                                    .makeText(AddActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }
    }

    private void addFirestore(){
        storageReference.child("users/me/profile.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String generatedFilePath = uri.toString();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
}