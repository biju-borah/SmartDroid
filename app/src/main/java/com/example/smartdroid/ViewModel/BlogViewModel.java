package com.example.smartdroid.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartdroid.Model.Recipe;
import com.example.smartdroid.Repository.BlogRepository;
import com.example.smartdroid.Repository.RecipeRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BlogViewModel extends ViewModel {
    MutableLiveData<ArrayList<Recipe>> recipeListMutableLiveData;
    FirebaseFirestore mFirestore;
    BlogRepository recipeRepository;

    public BlogViewModel() {
        recipeRepository = new BlogRepository();
        recipeListMutableLiveData =  recipeRepository.getRecipeListMutableLiveData();
        mFirestore = FirebaseFirestore.getInstance();
    }


    public MutableLiveData<ArrayList<Recipe>> getLiveRecipeData() {
        return recipeListMutableLiveData;
    }
}
