package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Dog.class},version=1)

public  abstract class DogsDB extends RoomDatabase {
    public abstract DogsDAO dogsDAO();

}
