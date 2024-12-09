package com.example.seminar9;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JSONact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jsonact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {

                    HttpURLConnection connection = null;
                    try {
                        URL url = new URL("https://pdm.ase.ro/situatii.json");
                        connection = (HttpURLConnection) url.openConnection();

                       InputStream is = connection.getInputStream();
                        InputStreamReader inputStreamReader=new InputStreamReader(is);
                        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

                        StringBuilder sb=new StringBuilder();
                        String line =null;

                        while((line=bufferedReader.readLine())!=null){
                            sb.append(line);
                        }

                        JSONObject jsonObject=new JSONObject(bufferedReader.toString());
                        JSONArray jsonArray=jsonObject.getJSONArray("file");
                        JSONObject obj=jsonArray.getJSONObject(0);
                        String disciplina = obj.getString("disciplina");

                        String act = obj.getString("activitate");
                        String valoare = obj.getString("valoare");
                        String pondere = obj.getString("pondere");
                        String data = obj.getString("data");
                        String desc = obj.getString("descriere");







                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });

    }
}