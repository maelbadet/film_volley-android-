package com.example.filmrecyclerview;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {

    public TextView titre;
    public TextView Description;
    public TextView TitreOriginal;
    public ImageView UrlImage;
    public TextView DateSortie;
    public TextView LangueOriginale;



    public Holder(@NonNull View itemView) {
        super(itemView);
        titre = itemView.findViewById(R.id.titre);
        Description = itemView.findViewById(R.id.descriptif);
        TitreOriginal = itemView.findViewById(R.id.TitreOriginal);
        UrlImage = itemView.findViewById(R.id.UrlImage);
        DateSortie = itemView.findViewById(R.id.DateSortie);
        LangueOriginale = itemView.findViewById(R.id.LangueOriginale);
    }


}
