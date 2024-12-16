package com.example.fridgecheck;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private IngredientAdapter ingredientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.ingredient_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ingredientAdapter = new IngredientAdapter();
        recyclerView.setAdapter(ingredientAdapter);

        // Initialize ViewModel
        IngredientViewModel ingredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);

        // Observe ingredient data
        ingredientViewModel.getAllIngredients().observe(this, ingredients -> {
            ingredientAdapter.submitList(ingredients);
            scheduleNotifications(ingredients);
        });

        // Floating Action Button to add new ingredient
        findViewById(R.id.add_ingredient_button).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddIngredientActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Schedule notifications for all ingredients
     */
    @SuppressLint("ScheduleExactAlarm")
    private void scheduleNotifications(List<Ingredient> ingredients) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        for (Ingredient ingredient : ingredients) {
            Calendar expirationDate = getExpirationDate(ingredient.getExpirationDate());
            if (expirationDate == null) {
                Log.e("NotificationTest", "Invalid or past expiration date for: " + ingredient.getName());
                continue;
            }

            Intent intent = new Intent(this, NotificationReceiver.class);
            intent.putExtra("ingredient_name", ingredient.getName());

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this,
                    ingredient.getId(), // Use unique ID for each ingredient
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            if (alarmManager != null) {
                alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        expirationDate.getTimeInMillis(),
                        pendingIntent
                );
                Log.d("NotificationTest", "Notification scheduled for: " + expirationDate.getTime()
                        + " for ingredient: " + ingredient.getName());
            } else {
                Log.e("NotificationTest", "AlarmManager is null. Notification not scheduled for: " + ingredient.getName());
            }
        }
    }

    /**
     * Convert expiration date string to Calendar object
     */
    private Calendar getExpirationDate(String expirationDate) {
        try {
            String[] parts = expirationDate.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]) - 1; // Month is 0-based
            int year = Integer.parseInt(parts[2]);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.HOUR_OF_DAY, 18); // Notify at 13:10
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
                Log.e("NotificationTest", "Skipping expired date: " + expirationDate);
                return null;
            }

            return calendar;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Invalid date format: " + expirationDate, Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
