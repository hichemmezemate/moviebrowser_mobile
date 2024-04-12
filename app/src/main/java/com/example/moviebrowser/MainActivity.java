package com.example.moviebrowser;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.moviebrowser.fragments.DetailFragment;
import com.example.moviebrowser.fragments.FilmFragment;
import com.example.moviebrowser.models.Film;
import com.example.moviebrowser.services.SearchObserver;

public class MainActivity extends AppCompatActivity  implements SearchObserver {

    private FilmFragment filmFragment;
    private DetailFragment detailFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filmFragment = new FilmFragment();
        filmFragment.setListener(this);
        detailFragment = new DetailFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, filmFragment)
                .add(R.id.frameLayout, detailFragment)
                .hide(detailFragment)
                .commit();
    }

    @Override
    public void onReceiveFilmInfo(Film film) {
        getSupportFragmentManager().beginTransaction()
                .hide(filmFragment)
                .show(detailFragment)
                .commit();
        detailFragment.onSelectFilm(film);

    }
}