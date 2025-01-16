package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListOnlineDogs extends AppCompatActivity {

    List<Bitmap> imagini = null;
    List<ItemImagine> imgItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_online_dogs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        List<String> linkuriImagini = new ArrayList<>();
        imagini = new ArrayList<>();
        //Bichon
linkuriImagini.add("https://rasedecaini.ro/wp-content/uploads/2019/05/rasa-bichon-maltez-730x438.jpg");

//        Rotweiller
        linkuriImagini.add("https://mediacdn.libertatea.ro/unsafe/870x0/smart/filters:format(webp):contrast(8):quality(75)/https://static4.libertatea.ro/wp-content/uploads/2020/09/caine-de-rasa.jpg");


//        Golden retriever
        linkuriImagini.add("https://i0.wp.com/www.cagt.co.uk/wp-content/uploads/2021/11/GoldenRetrieverSquare500px.jpg?fit=500%2C500&ssl=1");

//        maidanez
        linkuriImagini.add("https://www.viata-libera.ro/media/k2/items/cache/346b948c527f1f16ef45fd6c2689ef7c_XL.jpg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link: linkuriImagini){
                    HttpURLConnection connection = null;
                    try{
                        URL url = new URL(link);
                        connection = (HttpURLConnection) url.openConnection();
                        InputStream is = connection.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));

                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imgItems = new ArrayList<>();
                        imgItems.add(new ItemImagine("Bichon care", imagini.get(0), "https://totuldesprebichoni.ro/"));
                        imgItems.add(new ItemImagine("Rotweiller care", imagini.get(1), "https://animax.ro/blogs/lumea-animax/rottweiler"));
                        imgItems.add(new ItemImagine("Golden retriever care", imagini.get(2), "https://www.zooplus.ro/ghid/caini/rase-de-caini/golden-retriever"));
                        imgItems.add(new ItemImagine("Maidanez care", imagini.get(3), "https://www.royalcanin.com/ro/dogs/thinking-of-getting-a-dog/how-to-care-for-a-dog"));

                        ListView lv = findViewById(R.id.lv_online_workouts);
                        ImagineAdapter adapter = new ImagineAdapter(getApplicationContext(), R.layout.imagine_list_item, imgItems);
                        lv.setAdapter(adapter);

                    }
                });
            }
        });

        ListView lv = findViewById(R.id.lv_online_workouts);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                it.putExtra("link", imgItems.get(position).getLink());
                startActivity(it);
            }
        });

    }
}