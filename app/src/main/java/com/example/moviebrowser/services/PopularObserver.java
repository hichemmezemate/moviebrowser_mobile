package com.example.moviebrowser.services;

import com.example.moviebrowser.models.Film;

public interface PopularObserver {
    void onReceivePopularInfo(Film film);
}
