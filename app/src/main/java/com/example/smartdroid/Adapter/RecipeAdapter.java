package com.example.smartdroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartdroid.MainActivity;
import com.example.smartdroid.Model.Recipe;
import com.example.smartdroid.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{

    ArrayList<Recipe> recipes;

    public RecipeAdapter(ArrayList<Recipe> recipes){
        this.recipes = recipes;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(recipes.get(position).getName());
        View view = LayoutInflater.from(holder.tagLayout.getContext()).inflate(R.layout.tag,null,false);
        for (String i:recipes.get(position).getTag()) {

            TextView tv = view.findViewById(R.id.tagName);
            tv.setText(i);
            System.out.println(i);
            holder.tagLayout.addView(tv);
        }
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        LinearLayout tagLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cardName);
            tagLayout = itemView.findViewById(R.id.tagLayout);
        }
    }
}
