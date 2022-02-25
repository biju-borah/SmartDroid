package com.example.smartdroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.smartdroid.Activity.OnBoardingActivity;
import com.example.smartdroid.Activity.SignInActivity;
import com.example.smartdroid.ViewModel.RecipeViewModel;

public class MainActivity extends AppCompatActivity {

    RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        startActivity(new Intent(MainActivity.this, OnBoardingActivity.class));

//        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
//
//        recipeViewModel.getLiveRecipeData().observe(this,recipes -> {
//            System.out.println(recipes);
//            Toast.makeText(MainActivity.this, "This works", Toast.LENGTH_SHORT).show();
//        });
    }
}