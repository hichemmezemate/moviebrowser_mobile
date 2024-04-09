package com.example.moviebrowser.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviebrowser.models.Film;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Api {

    // "https://api.themoviedb.org/3/search/movie?api_key=58507cc706ac67cc8ba97b098c38a449&query=${searchText}&include_adult=false&language=en-US&page=1"
    private static String URL_API_SEARCH="https://api.themoviedb.org/3/search/movie?api_key=58507cc706ac67cc8ba97b098c38a449&query=";

    private static String URL_API_INFO="https://api.themoviedb.org/3/movie/";

    private static String URL_KEY="?api_key=58507cc706ac67cc8ba97b098c38a449&language=en-US";

    private static String URL_SEARCH_PAGE="&include_adult=false&language=fr_FR&page=1";
    //private static String URL_END_FORMATJSON= "?format=json";

    private static String URL_POSTER="https://image.tmdb.org/t/p/w92";


    public static void searchRequest(Context context, String search, SearchObserver listener){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(URL_API_SEARCH + search + URL_SEARCH_PAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("results");
                            Log.d("RESULTS", jsonArray.toString());
                            for(int i=0; i<10; i++){
                                JSONObject object= jsonArray.getJSONObject(i);
                                getFilmInfo(context, object.getString("id"), listener);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

public static void getFilmInfo(Context context, String filmId, SearchObserver listener){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(URL_API_INFO + filmId + URL_KEY,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            //JSONObject jsonObjectFilm= jsonObject.getJSONObject("depute");
                            Film film= new Film(jsonObject.getInt("id"),
                                    jsonObject.getString("title"),
                                    jsonObject.getString("release_date"),
                                    jsonObject.getString("overview"),
                                    jsonObject.getInt("vote_average"),
                                    jsonObject.getString("original_language"),
                                    jsonObject.getString("poster_path")
                            );

                            Log.d("POSTER", film.getPoster_path());



                            ArrayList<String> listGenre= new ArrayList<String>();
                            for(int i=0; i<jsonObject.getJSONArray("genres").length(); i++){
                                JSONArray objectGenre = jsonObject.getJSONArray("genres");
                                JSONObject objectGenre2 = objectGenre.getJSONObject(i);
                                String genreName = objectGenre2.getString("name");
                                Log.d("GENRE", genreName);

                                listGenre.add(genreName);

                                //JSONArray object= jsonObject.getJSONArray("name");
                                //Log.d("ListGenre", listGenre.toString());
                                //JSONObject object1 = object.getJSONObject(i);
                                //listGenre.add(new Responsability(object1.getJSONObject("responsabilite").getString("organisme"), object1.getJSONObject("responsabilite").getString("fonction"), object1.getJSONObject("responsabilite").getString("debut_fonction") ));

                            }


                            listener.onReceiveFilmInfo(film);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    public static void loadFilmPoster(Context context, String poster_path, final ImageView imageView){
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageRequest request = new ImageRequest( URL_POSTER+poster_path+"",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imageView.setImageResource(android.R.drawable.ic_menu_gallery);
                    }
                });
        queue.add(request);
    }
}
