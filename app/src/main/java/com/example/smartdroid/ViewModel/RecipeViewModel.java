package com.example.smartdroid.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartdroid.Model.Recipe;
import com.example.smartdroid.Repository.RecipeRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class RecipeViewModel extends ViewModel {
    MutableLiveData<List<Recipe>> recipeListMutableLiveData;
    FirebaseFirestore mFirestore;
    RecipeRepository recipeRepository;

    public RecipeViewModel() {
        recipeRepository = new RecipeRepository();
        recipeListMutableLiveData=  recipeRepository.getRecipeListMutableLiveData();
        mFirestore = FirebaseFirestore.getInstance();
    }

    public MutableLiveData<List<Recipe>> getLiveRecipeData() {
        return recipeListMutableLiveData;
    }
}
