package com.example.smartdroid.Repository;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.smartdroid.Model.Recipe;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
    MutableLiveData<ArrayList<Recipe>> recipeListMutableLiveData;
    FirebaseFirestore firebaseFirestore;
    MutableLiveData<Recipe> recipeMutableLiveData;

    public RecipeRepository(){
        this.recipeListMutableLiveData = new MutableLiveData<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        recipeMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Recipe>> getRecipeListMutableLiveData() {
        firebaseFirestore.collection("Popular").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                ArrayList<Recipe> RecipeList = new ArrayList<>();
                for (QueryDocumentSnapshot doc : value) {
                    if (doc != null) {
                        RecipeList.add(doc.toObject(Recipe.class));
                    }
                }
                recipeListMutableLiveData.postValue(RecipeList);
            }
        });
        return recipeListMutableLiveData;
    }

    //delete blog

    //update blog
}
