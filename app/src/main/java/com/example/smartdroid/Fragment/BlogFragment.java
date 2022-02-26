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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartdroid.Activity.AddActivity;
import com.example.smartdroid.Activity.OnBoardingActivity;
import com.example.smartdroid.Adapter.RecipeAdapter;
import com.example.smartdroid.Model.Recipe;
import com.example.smartdroid.R;
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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference dbRef = db.collection("Blog");
        Query query = dbRef.whereEqualTo("author",FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().size() == 0){
                        System.out.println("Here");
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