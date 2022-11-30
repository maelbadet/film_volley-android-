package com.example.filmrecyclerview;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FilmDAO {

    //test d'insert dans la bdd
    @Insert
    void insertAll(Film... users);

    @Delete
    void delete(Film users);

    @Query("SELECT * FROM table_des_films")
    List<Film> getAll();


}
