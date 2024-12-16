package com.example.fridgecheck;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class IngredientViewModel extends AndroidViewModel {

    private IngredientRepository repository;
    private LiveData<List<Ingredient>> allIngredients;

    public IngredientViewModel(Application application) {
        super(application);
        repository = new IngredientRepository(application);
        allIngredients = repository.getAllIngredients();   }

    public LiveData<List<Ingredient>> getAllIngredients() {
        return allIngredients;
    }

    public void insert(Ingredient ingredient) {
        repository.insert(ingredient);
    }
}


