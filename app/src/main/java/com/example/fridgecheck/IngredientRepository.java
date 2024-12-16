package com.example.fridgecheck;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class IngredientRepository {

    private IngredientDao ingredientDao;
    private LiveData<List<Ingredient>> allIngredients;

    public IngredientRepository(Application application) {
        IngredientDatabase db = IngredientDatabase.getDatabase(application);
        ingredientDao = db.ingredientDao();
        allIngredients = ingredientDao.getAllIngredients();
    }

    public LiveData<List<Ingredient>> getAllIngredients() {
        return allIngredients;
    }

    public void insert(Ingredient ingredient) {
        IngredientDatabase.databaseWriteExecutor.execute(() ->{
            Log.d("IngredientRepository", "Inserting ingredient: " + ingredient.getName());
            ingredientDao.insert(ingredient);});

    }
}
