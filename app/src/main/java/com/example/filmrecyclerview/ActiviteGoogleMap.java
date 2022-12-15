package com.example.filmrecyclerview;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.filmrecyclerview.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActiviteGoogleMap extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private String JSON_URL = "https://data.culture.gouv.fr/api/records/1.0/search/?dataset=etablissements-cinematographiques&q=&facet=region_administrative&facet=genre&facet=multiplexe&facet=zone_de_la_commune&refine.region_administrative=NOUVELLE+AQUITAINE&refine.genre=FIXE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_google_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        RequestQueue queu = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("records");
                    Log.i("mael", jsonArray+"");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        //Ca passe jusqu'ici
                        JSONObject id = jsonArray.getJSONObject(i);

                        JSONObject fields = id.getJSONObject("fields");
                        String nomCine = fields.getString("nom");

                        //Variable pour les coordonnées  du cinéma
                        double longitude = fields.getJSONArray("geolocalisation").getDouble(1);
                        double latitude = fields.getJSONArray("geolocalisation").getDouble(0);

                        //variable pour les écrans
                        int ecran = fields.getInt("ecrans");

                        LatLng cine = new LatLng(latitude,longitude);
                        if (ecran < 5){
                            
                            mMap.addMarker(new MarkerOptions().position(cine).title("nom cinéma : "+nomCine));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(cine));
                        }



                        mMap.addMarker(new MarkerOptions().position(cine).title("nom cinéma : "+nomCine));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(cine));


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Erreur de connexion au serveur",
                        Toast.LENGTH_SHORT).show();
            }
        }
        );
        queu.add(jsonObjectRequest);

/*
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }


}

      