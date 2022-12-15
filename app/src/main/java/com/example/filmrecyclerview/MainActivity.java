package com.example.filmrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //création de la liste de films
    ArrayList<Film> realisationFilm;
    //implémentation du recycler view
    RecyclerView recyclerView;

    //adresse de l'api pourla récupération des données
    private String JSON_URL = "https://api.themoviedb.org/3/movie/upcoming?api_key=1bc1b625f1329069b87b0cbd2133725a&language=fr-FR&page=1";
    //adresse de l'url du casting
    private String url_Casting = "https://api.themoviedb.org/3/movie/id/credits";

    private Button detailFilm;
    private Button laCarte;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.listeItems);
        realisationFilm = new ArrayList<Film>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemsAdapter adapter = new ItemsAdapter(realisationFilm);
        recyclerView.setAdapter((RecyclerView.Adapter) adapter);

        this.laCarte = findViewById(R.id.carte);
        laCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageCarte = new Intent(getApplicationContext(), ActiviteGoogleMap.class);
                startActivity(pageCarte);
            }
        });



        RequestQueue queu = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject jsonObjectRequest) {
                            try {
                                BaseDeDonne db = Room.databaseBuilder(getApplicationContext(),
                                        BaseDeDonne.class, "les films").allowMainThreadQueries().build();
                                FilmDAO filmDao = db.filmDAO();

                                //Réupération des différents éléments de l'api en bouclant sur les différents id de résults
                                JSONArray jsonArray = jsonObjectRequest.getJSONArray("results");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject filmObject = jsonArray.getJSONObject(i);
                                    String titre = filmObject.getString("title");
                                    String description = filmObject.getString("overview");
                                    String titreOriginal = filmObject.getString("original_title");
                                    String urlimage = filmObject.getString("poster_path");
                                    String dateSortie = filmObject.getString("release_date");
                                    String langueOriginal = filmObject.getString("original_language");

                                    Film lesfilms = new Film(filmObject.getInt("id"), titre, description, titreOriginal, urlimage, dateSortie, langueOriginal);
                                    //affichage sur l'écran
                                    adapter.add(lesfilms);
                                    //insertion dans la base de données Room
                                    filmDao.insertAll(lesfilms);
                                    adapter.notifyDataSetChanged();

                                }
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Erreur de connexion au serveur",
                        Toast.LENGTH_SHORT).show();
            }
        });
        queu.add(jsonObjectRequest);


        //redirection vers la page détails
      /*  detailFilm = findViewById(R.id.titre);
        detailFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View detailFilm) {
                Intent i = new Intent(getApplicationContext(), DetailFilms.class);
                startActivity(i);
//                finish();
            }
        });*/
    }



    }