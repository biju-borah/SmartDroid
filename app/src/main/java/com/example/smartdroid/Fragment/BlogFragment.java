package com.example.smartdroid.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartdroid.Activity.AddActivity;
import com.example.smartdroid.Activity.OnBoardingActivity;
import com.example.smartdroid.Adapter.RecipeAdapter;
import com.example.smartdroid.Model.Recipe;
import com.example.smartdroid.R;
import com.example.smartdroid.ViewModel.BlogViewModel;
import com.example.smartdroid.ViewModel.RecipeViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BlogFragment extends Fragment {

    FloatingActionButton add;
    RecyclerView recyclerView;
    RecipeAdapter recipeAdapter;
    BlogViewModel blogViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        blogViewModel = new ViewModelProvider(this).get(BlogViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blog, container, false);
        add = rootView.findViewById(R.id.floatingActionButton);
        recyclerView = rootView.findViewById(R.id.blogRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddActivity.class));

            }
        });
        setView();
        return rootView;
    }

    private void setView(){

        blogViewModel.getLiveRecipeData().observe(getViewLifecycleOwner(),recipes -> {

            recipeAdapter = new RecipeAdapter(recipes);
            recyclerView.setAdapter(recipeAdapter);
            recipeAdapter.notifyDataSetChanged();


        });
    }
}