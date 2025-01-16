package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DogsAdopt extends AppCompatActivity {

    public DogsDB database = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dogs_adopt);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent startedFromIntent=getIntent();
if(startedFromIntent.hasExtra("dogs")){
Dog d = startedFromIntent.getParcelableExtra("dogs");

    EditText nume = findViewById(R.id.nume);

    Spinner spinRasa=findViewById(R.id.rasa);

    EditText editGr=findViewById(R.id.greutate);

    EditText editV=findViewById(R.id.varsta);

    CheckBox checkAd=findViewById(R.id.adopt);
    String[] raseArray = getResources().getStringArray(R.array.rase);
    int desiredPosition = Arrays.asList(raseArray).indexOf(d.getRasa());
spinRasa.setSelection(desiredPosition);


nume.setText(String.valueOf(d.getNume()));

editGr.setText(String.valueOf(d.getGreutate()));
editV.setText(String.valueOf(d.getVarsta()));

//    String numele=nume.getText().toString();
//    String rasa=spinRasa.getSelectedItem().toString();
//    Float greutate=Float.parseFloat(editGr.getText().toString());
//    int varsta=Integer.parseInt(editV.getText().toString());
//    boolean adopt= checkAd.isChecked();
//
//    Dog c =new Dog(varsta,rasa,numele,greutate,adopt);
}
        database = Room.databaseBuilder(getApplicationContext(), DogsDB.class, "DogDatabase").build();


        Button submit = findViewById(R.id.butonAdoptie);
submit.setOnClickListener(v -> {
    EditText nume = findViewById(R.id.nume);
    String numele=nume.getText().toString();

    Spinner spinRasa=findViewById(R.id.rasa);
    String rasa=spinRasa.getSelectedItem().toString();

    EditText editGr=findViewById(R.id.greutate);
    Float greutate=Float.parseFloat(editGr.getText().toString());

    EditText editV=findViewById(R.id.varsta);
    int varsta=Integer.parseInt(editV.getText().toString());

    CheckBox checkAd=findViewById(R.id.adopt);
    boolean adopt= checkAd.isChecked();

    Dog dog =new Dog(varsta,rasa,numele,greutate,adopt);

    Executor executor= Executors.newSingleThreadExecutor();
    executor.execute(new Runnable() {
        @Override
        public void run() {
            try {
                FileOutputStream fs= openFileOutput("dogs.txt",MODE_PRIVATE);
                OutputStreamWriter out= new OutputStreamWriter(fs);
                BufferedWriter writer= new BufferedWriter(out);
                writer.write((dog.toString()));

                writer.close();
                out.close();
                fs.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            database.dogsDAO().insertDogs(dog);

        }
    });
    Intent it = new Intent();
    it.putExtra("dogs",dog);
    setResult(RESULT_OK,it);
    finish();
});



    }
}