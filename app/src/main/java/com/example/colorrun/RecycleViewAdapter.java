package com.example.colorrun;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {


    private List<Joueur> listPlayer;


    public RecycleViewAdapter(List<Joueur> listPlayer) {
        this.listPlayer = listPlayer;
    }

    @Override
    public int getItemCount() {
        return listPlayer.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Joueur joueur = listPlayer.get(position);
        holder.display(joueur);
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView score;
        private final TextView level;

        private Joueur currentJoueur;

        public MyViewHolder(final View itemView) {
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.name_player));
            score = ((TextView) itemView.findViewById(R.id.score_player));
            level = ((TextView) itemView.findViewById(R.id.level_player));


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentJoueur.getName())
                            .setMessage(currentJoueur.getScore())
                            .show();
                }
            });

        }

        public void display(Joueur joueur) {
            currentJoueur = joueur;
            name.setText(joueur.getName());
            score.setText(String.valueOf(joueur.getScore()));
            level.setText(String.valueOf(joueur.getLevel()));
        }
    }
}


