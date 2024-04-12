/*
package com.example.moviebrowser.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviebrowser.R;
import com.example.moviebrowser.services.Api;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    private ArrayList<Film> films;
    private Context context;

    public PopularAdapter(ArrayList<Film> films, Context context) {
        this.films = films;
        this.context = context;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }

    @Override
    public int getCount() {
        return films.size();
    }

    @Override
    public Object getItem(int position) {
        return films.get(position);
    }

    @Override
    public long getItemId(int position) {
        return films.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_film_card, parent, false);
        }

        //System.out.println(">>>>>>>>>>>> : " +films.get(position).getTitle());

        TextView textViewName= convertView.findViewById(R.id.popFilmText);
        textViewName.setText(films.get(position).getTitle()+ "");

        ImageView imageView= convertView.findViewById(R.id.popFilmImage);
        Api.loadFilmPoster(context, films.get(position).getPoster_path(), imageView);

        return convertView;
    }
}
*/
