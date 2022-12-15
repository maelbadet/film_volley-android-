package com.example.filmrecyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter< Holder > {

    public ArrayList<Film> listeDonnees = new ArrayList<Film>();

    public ItemsAdapter(ArrayList<Film> realisationFilm) {
        this.listeDonnees = realisationFilm;
    }

    public void add(Film lesfilms) {
        this.listeDonnees.add(lesfilms);
    }


    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.affichefilm, parent, false) );
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        //on fixe chaque éléments sur chaques textes view pour les afficher au propre

        holder.titre.setText( this.listeDonnees.get(position).getTitre() );
        holder.TitreOriginal.setText( this.listeDonnees.get(position).getDescription() );
        holder.Description.setText( this.listeDonnees.get(position).getTitreOriginal() );
        holder.DateSortie.setText( this.listeDonnees.get(position).getDateSortie() );
        holder.LangueOriginale.setText( this.listeDonnees.get(position).getLangueOriginale() );

        //mise en place de glide pour l'affichage des images
        Glide.with(holder.UrlImage)
                .load("https://image.tmdb.org/t/p/original"+this.listeDonnees.get(position).getUrlImage())
                .into(holder.UrlImage);

    }



    @Override
    public int getItemCount() {
        return this.listeDonnees.size();
    }

    public ArrayList<Film> getListeDonnees() {
        return listeDonnees;
    }

}
