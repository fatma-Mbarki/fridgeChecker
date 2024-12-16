package com.example.fridgecheck;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredients = new ArrayList<>();

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_item, parent, false);
        return new IngredientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient current = ingredients.get(position);
        holder.nameTextView.setText(current.getName());
        holder.dateTextView.setText(current.getExpirationDate());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void submitList(List<Ingredient> newIngredients) {
        ingredients = newIngredients;
        notifyDataSetChanged();
    }

    static class IngredientViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView, dateTextView;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.ingredient_name);
            dateTextView = itemView.findViewById(R.id.expiration_date);
        }
    }
}
