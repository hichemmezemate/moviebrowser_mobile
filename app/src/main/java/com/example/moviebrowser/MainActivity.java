package com.example.moviebrowser;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.moviebrowser.fragments.DetailFragment;
import com.example.moviebrowser.fragments.FilmFragment;
import com.example.moviebrowser.fragments.PopularFragment;
import com.example.moviebrowser.models.Film;
import com.example.moviebrowser.services.Api;
import com.example.moviebrowser.services.SearchObserver;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements SearchObserver {

    private FilmFragment filmFragment;
    private DetailFragment detailFragment;

    private PopularFragment popularFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filmFragment = new FilmFragment();

        filmFragment.setListener(this);
        detailFragment = new DetailFragment();
        popularFragment = new PopularFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, popularFragment)
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