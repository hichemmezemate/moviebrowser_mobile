package com.example.moviebrowser.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviebrowser.R;
import com.example.moviebrowser.models.Film;
import com.example.moviebrowser.services.Api;


public class DetailFragment extends Fragment {

    private TextView title, overview, releaseDate, voteAverage;
    private ImageView imageView;
    private Api api;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, null);
        title = v.findViewById(R.id.filmItemTitle);
        overview = v.findViewById(R.id.filmItemDescription);
        imageView = v.findViewById(R.id.imageView5);
        releaseDate = v.findViewById(R.id.filmReleaseDate);
        voteAverage = v.findViewById(R.id.filmVoteAverage);

        return v;

    }

    public void onSelectFilm(Film film) {
        title.setText(film.getTitle());
        overview.setText(film.getOverview());
        releaseDate.setText("Date de sortie: " + film.getRelease_date());
        voteAverage.setText(film.getVote_average()+"/10");
        Api.loadFilmPoster(getContext(), film.getPoster_path(), imageView);
    }
}