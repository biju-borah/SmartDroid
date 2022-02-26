package com.example.smartdroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartdroid.Adapter.RecipeAdapter;
import com.example.smartdroid.Model.Recipe;
import com.example.smartdroid.R;
import com.example.smartdroid.ViewModel.RecipeViewModel;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecipeViewModel recipeViewModel;
    RecipeAdapter recipeAdapter;
    RecyclerView recyclerView;
    private CarouselView carouselView;

    int ad[] = {R.drawable.dummy_img};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.pickList);
        carouselView = rootView.findViewById(R.id.carouselView);
        carouselView.setPageCount(ad.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(ad[position]);
                Glide.with(getContext()).load(ad[position]).into(imageView);

            }
        });
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipeViewModel.getLiveRecipeData().observe(getViewLifecycleOwner(),recipes -> {

            recipeAdapter = new RecipeAdapter(recipes);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(recipeAdapter);
            recipeAdapter.notifyDataSetChanged();


        });

    }
}