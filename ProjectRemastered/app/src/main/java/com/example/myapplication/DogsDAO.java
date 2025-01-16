package com.example.myapplication;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


@Dao
public interface DogsDAO {
    @Insert
    public void insertDogs(Dog dog);

    @Query("Select * from dogs")
    public List<Dog> getDogs();
    @Delete
    void deleteDog(Dog dog);

}
