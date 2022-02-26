package com.example.smartdroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartdroid.Model.Recipe;
import com.example.smartdroid.R;

public class ShowDetailActivity extends AppCompatActivity {

    Recipe object;
    int star;
    TextView recipeName, shortDesc, authorName;
    ImageView imageView;
    LinearLayout starLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        object = (Recipe) getIntent().getSerializableExtra("object");
        star = (int)getIntent().getIntExtra("star",0);

        recipeName = findViewById(R.id.recipeName);
        shortDesc = findViewById(R.id.shortDesc);
        authorName = findViewById(R.id.authorName);
        starLayout = findViewById(R.id.star);
        imageView = findViewById(R.id.recipeImgLarge);

        recipeName.setText(object.getName());
        shortDesc.setText(object.getShortDesc());
        authorName.setText(object.getAuthor());
        Glide.with(this).load(object.getImgUrl()).into(imageView);

        initializeStar(star);
    }

    private void initializeStar(int stars){
        for (int i = 0; i < stars; i++) {
            ImageView img = new ImageView(this);
            img.setImageResource(R.drawable.ic_start);
            img.setMinimumWidth(50);
            img.setMinimumHeight(50);

            starLayout.addView(img);
        }
    }
}