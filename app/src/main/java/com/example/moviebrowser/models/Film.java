package com.example.moviebrowser.models;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Film {

    private Integer id;

    private String title;

    private String release_date;

    private String overview;

    private Integer vote_average;

    private String original_language;

    private ArrayList<Integer> genre_ids;

    private String poster_path;



    public Film(Integer id, String title, String release_date, String overview, Integer vote_average, String original_language, String poster_path) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.overview = overview;
        this.vote_average = vote_average;
        this.original_language = original_language;
        this.poster_path = poster_path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getVote_average() {
        return vote_average;
    }

    public void setVote_average(Integer vote_average) {
        this.vote_average = vote_average;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Film other= (Film) obj;
        return other.getId().equals(this.getId());
    }
}
