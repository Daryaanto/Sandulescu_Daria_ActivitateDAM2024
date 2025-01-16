package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Dog> dogs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dogs = new ArrayList<>();

        Button btn= findViewById(R.id.buttonAdopt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), DogsAdopt.class);
                startActivityForResult(it,345);
            }
        });

        Button btnList = findViewById(R.id.buttonDogList);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), DogList.class);
                it.putParcelableArrayListExtra("dogs", (ArrayList<? extends Parcelable>) dogs);
                startActivity(it);
            }
        });
        Button btnOnlineDogs = findViewById(R.id.buttonOnlineDogs);
        btnOnlineDogs.setOnClickListener((View view)->{
            Intent it = new Intent(getApplicationContext(), ListOnlineDogs.class);
            startActivity(it);
        });

        Button btnSituatii = findViewById(R.id.buttonSituatii);
        btnSituatii.setOnClickListener((View view)->{
            Intent it = new Intent(getApplicationContext(), JsonParsingActivity.class);
            startActivity(it);
        });
        Button btnFav = findViewById(R.id.buttonFav);
        btnFav.setOnClickListener((View view)->{
            Intent it = new Intent(getApplicationContext(), ListaFavorite.class);
            startActivity(it);
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 345){
            if(resultCode == RESULT_OK){
                Dog dog = data.getParcelableExtra("dogs");
                dogs.add(dog);
                Toast.makeText(getApplicationContext(), dog.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}