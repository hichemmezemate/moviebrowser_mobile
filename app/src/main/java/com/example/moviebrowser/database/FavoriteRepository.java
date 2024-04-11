package com.example.moviebrowser.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.moviebrowser.models.Film;

import java.util.ArrayList;

public class FavoriteRepository {
    private DatabaseManager dbm;
    private static FavoriteRepository instance;

    private FavoriteRepository(Context context){
        this.dbm= DatabaseManager.getInstance(context);
    }

    public static FavoriteRepository getInstance(Context context){
        if(instance==null){
            instance= new FavoriteRepository(context);
        }
        return instance;
    }

    //ADD
    public boolean add(Film f) {
        if(isFavorite(f)) return false;
        ContentValues values = new ContentValues();
        values.put("id", f.getId());
        values.put("title", f.getTitle());
        values.put("release_date", f.getRelease_date());
        values.put("overview", f.getOverview());
        values.put("vote_average", f.getVote_average());
        values.put("original_language", f.getOriginal_language());
        values.put("poster_path", f.getPoster_path());
        long line= dbm.getWritableDatabase().insert("favorite", null, values);
        return line != 0;
    }

    //REMOVE
    public boolean remove(Film f) {
        String[] identifier = {String.valueOf(f.getId())};
        long line= dbm.getWritableDatabase().delete("favorite", "id=?", identifier);
        return line != 0;
    }

    //IS_FAVORITE
    public boolean isFavorite(Film f) {
        String[] identifier = {String.valueOf(f.getId())};
        Cursor c= dbm.getReadableDatabase().rawQuery("select * from favorite where id=?", identifier);
        return c.getCount()>0;
    }


    //GET ALL
    public ArrayList<Film> getAll() {
        ArrayList<Film> films = new ArrayList<Film>();
        Cursor c= dbm.getReadableDatabase().rawQuery("select * from favorite ", null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            Film f= new Film();
            f.setId(c.getInt(0));
            f.setTitle(c.getString(1));
            f.setRelease_date(c.getString(2));
            f.setOverview(c.getString(3));
            f.setVote_average(c.getInt(4));
            f.setOriginal_language(c.getString(5));
            f.setPoster_path(c.getString(6));
            films.add(f);
            c.moveToNext();
        }
        c.close();
        return films;
    }


}
