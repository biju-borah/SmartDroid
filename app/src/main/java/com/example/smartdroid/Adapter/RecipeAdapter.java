package com.example.smartdroid.Adapter;

import android.content.Intent;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartdroid.Activity.ShowDetailActivity;
import com.example.smartdroid.MainActivity;
import com.example.smartdroid.Model.Recipe;
import com.example.smartdroid.R;

import java.util.ArrayList;

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
        LinearLayout linearLayout = new LinearLayout(holder.title.getContext());
        LinearLayout linearLayoutReturns = new LinearLayout(holder.title.getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutReturns.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.RIGHT);
        linearLayoutReturns.setGravity(Gravity.RIGHT);

        double avg = 0;
        for (String n:recipes.get(position).getRating()) {
            avg += Integer.parseInt(n);
        }
        avg /= recipes.get(position).getRating().size();
        System.out.println(avg);

        for (int i = 0; i < (int)avg ; i++) {
            ImageView img = new ImageView(holder.title.getContext());
            img.setImageResource(R.drawable.ic_start);
            img.setMinimumWidth(50);
            img.setMinimumHeight(50);
            linearLayoutReturns.addView(img);
        }


        holder.tagLayout.addView(linearLayoutReturns);
        for (String i : recipes.get(position).getTag()) {
            TextView gb = new TextView(holder.title.getContext());
            TextView textView = new TextView(holder.title.getContext());
            textView.setBackgroundResource(R.drawable.tag);
            textView.setPadding(15,5,15,5);

            gb.setWidth(5);
            textView.setText(i);
            linearLayout.addView(gb);
            linearLayout.addView(textView);
        }

        holder.tagLayout.addView(linearLayout);

        Glide.with(holder.title.getContext()).load(recipes.get(position).getImgUrl()).into(holder.image);
        double finalAvg = avg;
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", recipes.get(holder.getAdapterPosition()));
                intent.putExtra("star",(int) finalAvg);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        LinearLayout tagLayout,card;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cardName);
            tagLayout = itemView.findViewById(R.id.plsWork);
            card = itemView.findViewById(R.id.card);
            image = itemView.findViewById(R.id.recipeImgSmall);
        }
    }
}
