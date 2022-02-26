package com.example.smartdroid.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartdroid.Activity.AddActivity;
import com.example.smartdroid.Adapter.RecipeAdapter;
import com.example.smartdroid.Model.Recipe;
import com.example.smartdroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    SearchView searchQuery;
    RecipeAdapter recipeAdapter;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        searchQuery = rootView.findViewById(R.id.searchView);
        recyclerView = rootView.findViewById(R.id.searchRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        search();
        return rootView;

    }

    private void search(){
        searchQuery.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchIt(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void  searchIt(String s){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference dbRef = db.collection("Popular");
        Query query = dbRef.whereArrayContains("tag",s);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().size() == 0){
                        System.out.println("Here");
                        Toast.makeText(getContext(), "No result found ... !", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    for(QueryDocumentSnapshot doc: task.getResult()){
                        ArrayList<Recipe> recipes = new ArrayList<>();
                        Recipe recipe = doc.toObject(Recipe.class);
                        recipes.add(recipe);
                        recipeAdapter = new RecipeAdapter(recipes);
                        recyclerView.setAdapter(recipeAdapter);
                        recipeAdapter.notifyDataSetChanged();
                        return;
                    }

                }else{
                    Toast.makeText(getContext(),"Something went wrong !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}