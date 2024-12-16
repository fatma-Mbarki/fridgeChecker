package com.example.fridgecheck;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddIngredientActivity extends AppCompatActivity {

    private EditText ingredientNameEditText;
    private TextView expirationDateTextView;
    private Button selectDateButton, saveButton;
    private String selectedDate = "";

    private IngredientViewModel ingredientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        ingredientNameEditText = findViewById(R.id.ingredient_name);
        expirationDateTextView = findViewById(R.id.expiration_date);
        selectDateButton = findViewById(R.id.select_date_button);
        saveButton = findViewById(R.id.save_button);

        ingredientViewModel = new IngredientViewModel(getApplication());

        selectDateButton.setOnClickListener(v -> openDatePicker());
        saveButton.setOnClickListener(v -> saveIngredient());
    }

    private void openDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            expirationDateTextView.setText("Expiration: " + selectedDate);
        }, year, month, day).show();
    }

    private void saveIngredient() {
        String name = ingredientNameEditText.getText().toString();

        if (name.isEmpty() || selectedDate.isEmpty()) {
            Toast.makeText(this, "Please provide both a name and an expiration date.", Toast.LENGTH_SHORT).show();
            return;
        }

        Ingredient ingredient = new Ingredient(name, selectedDate);
        ingredientViewModel.insert(ingredient);
        Toast.makeText(this, "Ingredient added!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
