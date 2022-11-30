package com.example.filmrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ArrayList<Film> realisationFilm = new ArrayList<Film>();

    TextView titre;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //on se met sur la vue du recycler
        setContentView(R.layout.activity_main);
        //renvoie sur le recycler permettant le deffilement des infos
        RecyclerView monRecycler = findViewById(R.id.listeItems);
        // on creer un nouvel item adapter
        ItemsAdapter test = new ItemsAdapter();
        // on met l'adapter dans le recycler
        monRecycler.setAdapter(test);
        //on utilise la liste de film pour l'afficher
        test.setListeDonnees(realisationFilm);

        titre = findViewById(R.id.json);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        BaseDeDonne db = Room.databaseBuilder(getApplicationContext(),
                BaseDeDonne.class, "les films").allowMainThreadQueries().build();
        FilmDAO filmDao = db.filmDAO();

        StringRequest requete = new StringRequest(Request.Method.GET,
                "https://api.themoviedb.org/3/movie/upcoming?api_key=1bc1b625f1329069b87b0cbd2133725a&language=fr-FR&page=1" ,
                new Response.Listener<String>() {

            @Override
                    public void onResponse(String response) {

                try {

                    JSONObject film = new JSONObject(response);
                    JSONArray  re1 = film.getJSONArray("results");

                    for (int i=0; i<re1.length();i++){
                        JSONObject film1 = re1.getJSONObject(i);
                        String titre_film = film1.getString("title");
                        String titreOriginal = film1.getString("original_title");
                        String description = film1.getString("overview");
                        String urlImage = film1.getString("poster_path");
                        String dateSortie = film1.getString("release_date");
                        String original_language = film1.getString("original_language");
                        Log.i("mael",i+"");
                        titre.setText("le titre : " + titre_film + " le titre originale : " + titreOriginal + "\n" +
                                " description : " + description + "\n" +
                                " photo de couverture : "+urlImage+"\n" +
                                " date de sortie : "+dateSortie + "\n" +
                                " langue originale : "+ original_language);

                        Film filmBDD = new Film(titre_film,titreOriginal,description,urlImage,dateSortie,original_language);
                        filmDao.insertAll(filmBDD);
                    }
                } catch (JSONException e) {
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
        queue.add(requete);

        List<Film> lesfilms = filmDao.getAll();


    }

    
}






 /* titreOriginal.setText(titreOriginal);
                        description.setText(description);
                        urlImage.setText(urlImage);
                        dateSortie.setText(dateSortie);
                        original_language.setText(original_language);*/


/*--- .append ne marche pas pour afficher une image ---*/
// titre.append("dklfjsldfkjsld");
// titre.append(titre_film);

/*--- comment faire un log avec un tag ---*/
// Log.i("Yann", "Apres 2");


/*
        Film film1 = new Film(
                "pas 1 goutte", "merlin", "description", "\"data:image/jpeg;base64,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\"\n"
                , "20/09/2008","Anglais");

        Film film2 = new Film("le nudiste", "camping","je sais pas","https://resize.programme-television.ladmedia.fr/r/670,670/img/var/premiere/storage/images/tele-7-jours/news-tv/camping-france-2-franck-dubosc-coeur-de-campeur-4306681/77624217-1-fre-FR/Camping-France-2-Franck-Dubosc-coeur-de-campeur.jpg"
                ,"26/04/2006","Francais");

        Film film3 = new Film("thomas au café chaud", "les bodins","vafenculo","https://fr.web.img2.acsta.net/pictures/21/09/28/16/27/4202972.jpg"

                ,"26/04/2006","Francais");
        Film film4 = new Film("ordure", "onePiece_RED","je sais pas","https://www.nautiljon.com/images/anime/00/42/one_piece_red_10824.jpg"
                ,"26/04/2006","Japonais");
        Film film5 = new Film("les ordures au café chaud", "woman","je sais pas","https://i.ytimg.com/vi/q2TreHWKCwY/maxresdefault.jpg"
                ,"26/04/2006","Americain");
        Film film6 = new Film("FF_LAND le come back", "projectX","je sais pas","https://www.instagram.com/p/Cii7vx0oXjU/?utm_source=ig_web_copy_link"
                ,"26/04/2006","Francais");

        realisationFilm.add(film1);
        realisationFilm.add(film2);
        realisationFilm.add(film3);
        realisationFilm.add(film4);
        realisationFilm.add(film5);
        realisationFilm.add(film6);*/