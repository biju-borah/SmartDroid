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

import com.example.smartdroid.Model.Recipe;
import com.example.smartdroid.Model.User;
import com.example.smartdroid.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class AddActivity extends AppCompatActivity {

    private Button btnSelect, btnUpload, addIngredient, addTag;

    private ImageView imageView;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 22;

    ArrayList<String> tag = new ArrayList<>();
    ArrayList<String> ingredient = new ArrayList<>();

    EditText inpIngredient, inpTag, inpShortDesc, inpLongDesc, inpName, inpUrl;

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
        inpShortDesc = findViewById(R.id.inpShortDesc);
        inpLongDesc = findViewById(R.id.inpLongDesc);
        inpName = findViewById(R.id.inpName);
        inpUrl = findViewById(R.id.inpUrl);

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
//        ingredient.add(inpIngredient.getText().toString());
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
        tag.add(inpTag.getText().toString());
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
                                                    "Recipe Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();

                                    addFirestore(uuid);
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

    private void addFirestore(String uuid){
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference dateRef = storageRef.child("images/" + uuid);
        storageRef.child("images/" + uuid).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                FirebaseFirestore db = db = FirebaseFirestore.getInstance();
                CollectionReference dbUser = db.collection("Blog");
                ArrayList<String> rating = new ArrayList<>();
                rating.add("0");
                Recipe recipe = new Recipe(inpName.getText().toString(),uri.toString(),inpShortDesc.getText().toString(),inpLongDesc.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),inpUrl.getText().toString(),rating,tag,ingredient);
                dbUser.add(recipe).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        startActivity(new Intent(AddActivity.this,ConfirmActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });


    }
}