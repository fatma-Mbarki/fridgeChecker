package com.example.fridgecheck;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Ingredient.class}, version = 1, exportSchema = false)
public abstract class IngredientDatabase extends RoomDatabase {


    private static volatile IngredientDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract IngredientDao ingredientDao();

    public static IngredientDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (IngredientDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    IngredientDatabase.class, "ingredient_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}


