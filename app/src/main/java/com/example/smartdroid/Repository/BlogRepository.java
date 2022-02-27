package com.example.smartdroid.Repository;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.smartdroid.Adapter.RecipeAdapter;
import com.example.smartdroid.Model.Recipe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BlogRepository {
    MutableLiveData<ArrayList<Recipe>> recipeListMutableLiveData;
    FirebaseFirestore firebaseFirestore;
    MutableLiveData<Recipe> recipeMutableLiveData;

    public BlogRepository(){
        this.recipeListMutableLiveData = new MutableLiveData<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        recipeMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Recipe>> getRecipeListMutableLiveData() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference dbRef = db.collection("Blog");
        Query query = dbRef.whereEqualTo("author", FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful() && task.getResult().size() != 0 ){
                    ArrayList<Recipe> recipeList = new ArrayList<>();
                    for(QueryDocumentSnapshot doc: task.getResult()){

                        Recipe recipe = doc.toObject(Recipe.class);
                        recipeList.add(recipe);

                    }
                    recipeListMutableLiveData.postValue(recipeList);

                }
            }
        });

        return recipeListMutableLiveData;
    }
}
