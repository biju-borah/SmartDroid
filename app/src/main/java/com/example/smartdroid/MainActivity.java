package com.example.smartdroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.smartdroid.Activity.OnBoardingActivity;
import com.example.smartdroid.Activity.SignInActivity;
import com.example.smartdroid.ViewModel.RecipeViewModel;
import com.example.smartdroid.Fragment.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        startActivity(new Intent(MainActivity.this, OnBoardingActivity.class));

//        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
//
//        recipeViewModel.getLiveRecipeData().observe(this,recipes -> {
//            System.out.println(recipes);
//            Toast.makeText(MainActivity.this, "This works", Toast.LENGTH_SHORT).show();
//        });
        Fragment home = new HomeFragment();
        Fragment search = new SearchFragment();
        Fragment account = new AccountFragment();
        Fragment blog = new BlogFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,home).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectFrag = null;
                switch (item.getItemId()){
                    case R.id.home:
                        selectFrag = home;
                        break;
                    case R.id.search:
                        selectFrag = search;
                        break;
                    case R.id.profile:
                        selectFrag = account;
                    case R.id.blog:
                        selectFrag = blog;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,selectFrag).commit();
                return true;
            }
        });
    }
}