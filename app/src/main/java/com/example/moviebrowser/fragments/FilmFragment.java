package com.example.moviebrowser.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.moviebrowser.R;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.moviebrowser.models.Film;
import com.example.moviebrowser.models.FilmAdapter;
import com.example.moviebrowser.services.Api;
import com.example.moviebrowser.services.SearchObserver;

import java.util.ArrayList;

public class FilmFragment extends Fragment implements SearchView.OnQueryTextListener, SearchObserver, AdapterView.OnItemClickListener{

    private SearchView searchView;
    private ListView listView;

    private FilmAdapter adapter;

    private ArrayList<Film> films;

    private SearchObserver listener;

    public void setListener(SearchObserver listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, null);

        searchView = v.findViewById(R.id.searchViewMain);
        searchView.setOnQueryTextListener(this);

        listView= v.findViewById(R.id.listViewFilm);
        films= new ArrayList<Film>();
        adapter= new FilmAdapter(films, getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return v;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        films= new ArrayList<Film>();
        Api.searchRequest(getContext(), query, this);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onReceiveFilmInfo(Film film) {
        if(!films.contains(film)){
            films.add(film);
            adapter= new FilmAdapter(films, getContext());
            listView.setAdapter(adapter);
        }

        Log.d("FILMS", films+"");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //listener.onReceiveFilmInfo(films
                //.get(position));
    }
}
