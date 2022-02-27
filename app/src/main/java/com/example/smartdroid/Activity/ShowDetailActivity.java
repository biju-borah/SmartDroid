package com.example.smartdroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Typeface;
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
    LinearLayout starLayout, llIng;

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
        llIng = findViewById(R.id.llIng);

        recipeName.setText(object.getName());
        shortDesc.setText(object.getShortDesc());
        authorName.setText(object.getAuthor());
        Glide.with(this).load(object.getImgUrl()).placeholder(R.drawable.loading).into(imageView);

        initializeStar(star);
        initializeIng();
    }

    private void initializeStar(int stars){
        for (int i = 0; i < stars; i++) {
            ImageView img = new ImageView(this);
            img.setImageResource(R.drawable.ic_start);
            img.setMinimumWidth(50);
            img.setMinimumHeight(50);

            starLayout.addView(img);
        }
        if(stars == 0){
            TextView txt = new TextView(this);
            txt.setTextColor(ContextCompat.getColor(this,R.color.body_text2));
            txt.setTypeface(txt.getTypeface(), Typeface.ITALIC);
            txt.setText("no rating");
            starLayout.addView(txt);
        }
    }

    private void initializeIng(){
        int len = object.getIngredients().size();
        for (int i = 0; i < len; i++) {
            TextView gb = new TextView(this);
            TextView textView = new TextView(this);
            textView.setBackgroundResource(R.drawable.tag);
            textView.setPadding(15,5,15,5);

            gb.setWidth(5);
            textView.setText(object.getIngredients().get(i));
            textView.setTextSize(20);
            llIng.addView(gb);
            llIng.addView(textView);

        }
    }
}