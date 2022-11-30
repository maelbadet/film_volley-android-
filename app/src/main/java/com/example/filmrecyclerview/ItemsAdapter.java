package com.example.filmrecyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter< Holder >{

    public ArrayList<Film> listeDonnees = new ArrayList<Film>();

    public void setListeDonnees(ArrayList<Film> l ){
        this.listeDonnees = l;
    }

    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.affichefilm, parent, false) );
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.titre.setText( this.listeDonnees.get(position).getTitre() );
        holder.Description.setText( this.listeDonnees.get(position).getTitreOriginal() );
        holder.TitreOriginal.setText( this.listeDonnees.get(position).getDescription() );
        holder.UrlImage.setText( this.listeDonnees.get(position).getUrlImage() );
        holder.DateSortie.setText( this.listeDonnees.get(position).getDateSortie() );
        holder.LangueOriginale.setText( this.listeDonnees.get(position).getLangueOriginale() );
    }

    @Override
    public int getItemCount() {
        return this.listeDonnees.size();
    }
}

