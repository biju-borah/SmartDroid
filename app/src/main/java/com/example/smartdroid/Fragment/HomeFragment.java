package com.example.smartdroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartdroid.Adapter.RecipeAdapter;
import com.example.smartdroid.Model.Recipe;
import com.example.smartdroid.R;
import com.example.smartdroid.ViewModel.RecipeViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecipeViewModel recipeViewModel;
    RecipeAdapter recipeAdapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

//        recipeViewModel.getLiveRecipeData().observe(this,recipes -> {
//
//            recipeAdapter = new RecipeAdapter(recipes);
//            recyclerView.setAdapter(recipeAdapter);
//            recipeAdapter.notifyDataSetChanged();
//
//        });


    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.pickList);
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Biju",new ArrayList<String>(){
            {
                add("Geeks");
                add("for");
                add("Geeks");
            }
        }));
        System.out.println("hello");
        recipeAdapter = new RecipeAdapter(recipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(recipeAdapter);
        recipeAdapter.notifyDataSetChanged();

    }
}