package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DogList extends AppCompatActivity {
private  DogsAdapter adapter = null;
private  int idMod=0;
List<Dog> dogs=null;

public DogsDB database=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dog_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        dogs = new ArrayList<>();

        database= Room.databaseBuilder(getApplicationContext(),DogsDB.class, "DogsDB").build();
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<Dog> stored = database.dogsDAO().getDogs();
                dogs.addAll(stored);
               //adapter.notifyDataSetChanged();
            }
        });

        ListView lv = findViewById(R.id.list);
        adapter = new DogsAdapter(dogs, getApplicationContext(), R.layout.list_item_dogs);
        lv.setAdapter(adapter);
       // adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("long click", " click");
                Intent modifDogs = new Intent(getApplicationContext(), DogsAdopt.class);
                modifDogs.putExtra("dogs" , dogs.get(position));
                int idMod = position;
                startActivityForResult(modifDogs, 234);

            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("long click", "Long click");
                // Shared Preferences
                SharedPreferences sp = getSharedPreferences("dogsFavorite", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(String.valueOf(dogs.get(position).getId()), dogs.get(position).toString());
                editor.apply();
                return false;
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode==234){
            dogs.set(idMod, data.getParcelableExtra("dogs"));
            adapter.notifyDataSetChanged();
        }
    }
}