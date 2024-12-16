package com.example.fridgecheck;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IngredientDao {

    @Insert
    void insert(Ingredient ingredient);

    @Query("SELECT * FROM ingredient_table ORDER BY expiration_date ASC")
    LiveData<List<Ingredient>> getAllIngredients();
}
