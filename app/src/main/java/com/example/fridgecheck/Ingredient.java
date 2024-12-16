package com.example.fridgecheck;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient_table")
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "ingredient_name")
    private final String name;
    @ColumnInfo(name = "expiration_date")
    private  String expirationDate;

    public Ingredient(String name, String expirationDate) {
        this.name = name;
        this.expirationDate = expirationDate;
    }


    public void setId(int id) {
        this.id = id;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getExpirationDate() { return expirationDate; }
}
