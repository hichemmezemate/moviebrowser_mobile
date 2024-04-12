package com.example.moviebrowser.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.moviebrowser.R;
import com.example.moviebrowser.models.Film;
import com.example.moviebrowser.models.PopularAdapter;
import com.example.moviebrowser.services.Api;
import com.example.moviebrowser.services.PopularObserver;
import com.example.moviebrowser.services.SearchObserver;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PopularFragment extends Fragment implements PopularObserver {

    private RecyclerView filmCardListView;


    private PopularAdapter adapter;

    private ArrayList<Film> films;

    private PopularObserver listener;

    public void setListener(PopularObserver listener) {
        this.listener = listener;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.popular_fragment, null);

        filmCardListView = v.findViewById(R.id.filmCardListView);

        films = new ArrayList<Film>();


        Api.getPopular(getContext(), this);
        adapter = new PopularAdapter(films, getContext());
        //System.out.println("<<<<<<< popular F : "+films.toString());
        adapter.setFilms(films);
        filmCardListView.setAdapter( adapter );

        filmCardListView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        return v;
    }

    @Override
    public void onReceivePopularInfo(Film film) {
        System.out.println("OnRECEIVE 2");
        if(!films.contains(film)){
            films.add(film);
            adapter= new PopularAdapter(films, getContext());
            filmCardListView.setAdapter(adapter);
        }
    }
}
