package com.example.filmrecyclerview;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Film.class}, version = 1)
public abstract class BaseDeDonne extends RoomDatabase {

    public abstract FilmDAO filmDAO();

}
