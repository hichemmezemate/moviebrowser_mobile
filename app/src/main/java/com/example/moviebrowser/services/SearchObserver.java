package com.example.moviebrowser.services;

import com.example.moviebrowser.models.Film;

public interface SearchObserver {
    public void onReceiveFilmInfo(Film film);
}
