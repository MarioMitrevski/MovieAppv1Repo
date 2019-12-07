package com.example.movieappv1.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class Movie {

    @ColumnInfo(name = "custom_title")
    public String Title;
    public String Year;

    @PrimaryKey
    @NonNull
    public String imdbID;

    public String Type;
    public String Poster;


}
