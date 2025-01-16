package com.example.seminar9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class adaugare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adaugare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button butonAdopta=findViewById(R.id.butonAdoptie);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");
        butonAdopta.setOnClickListener(view -> {
            //Toast.makeText(getApplicationContext(),"Submit",Toast.LENGTH_LONG).show();
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

            Caine c =new Caine(varsta,rasa,numele,greutate,adopt);

            Intent it = new Intent();
            it.putExtra("Catel",  c);
            setResult(RESULT_OK, it);
            finish();
            CheckBox checkFb=findViewById(R.id.disp);

            boolean fb= checkFb.isChecked();
            if(fb) {
                myRef.child("catelusi").child(String.valueOf(c.getNume())).setValue(c);
            //myRef.updateChildren((Map<String, Object>) c);
                Toast.makeText(getApplicationContext(),"Adaugat",Toast.LENGTH_LONG).show();

            }
        });
    }
}