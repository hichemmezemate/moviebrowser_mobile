package com.example.moviebrowser.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviebrowser.fragments.PopularFragment;
import com.example.moviebrowser.models.Film;
import com.example.moviebrowser.models.PopularAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Api {
    private static String URL_API_SEARCH="https://api.themoviedb.org/3/search/movie?api_key=58507cc706ac67cc8ba97b098c38a449&query=";

    private static String URL_API_POPULAR="https://api.themoviedb.org/3/movie/popular?api_key=58507cc706ac67cc8ba97b098c38a449&language=fr-FR&page=1";

    private static String URL_API_INFO="https://api.themoviedb.org/3/movie/";

    private static String URL_KEY="?api_key=58507cc706ac67cc8ba97b098c38a449&language=en-US";

    private static String URL_SEARCH_PAGE="&include_adult=false&language=fr_FR&page=1";

    private static String URL_POSTER="https://image.tmdb.org/t/p/w";


    public static void searchRequest(Context context, String search, SearchObserver listener){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(URL_API_SEARCH + search.toLowerCase() + URL_SEARCH_PAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response.toString());
                            JSONArray jsonArray= jsonObject.getJSONArray("results");
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

                            listener.onReceiveFilmInfo(film);


                            ArrayList<String> listGenre= new ArrayList<String>();
                            for(int i=0; i<jsonObject.getJSONArray("genres").length(); i++){
                                JSONArray objectGenre = jsonObject.getJSONArray("genres");
                                JSONObject objectGenre2 = objectGenre.getJSONObject(i);
                                String genreName = objectGenre2.getString("name");

                                listGenre.add(genreName);

                                //JSONArray object= jsonObject.getJSONArray("name");
                                //Log.d("ListGenre", listGenre.toString());
                                //JSONObject object1 = object.getJSONObject(i);
                                //listGenre.add(new Responsability(object1.getJSONObject("responsabilite").getString("organisme"), object1.getJSONObject("responsabilite").getString("fonction"), object1.getJSONObject("responsabilite").getString("debut_fonction") ));

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

    public static void loadFilmPoster(Context context, String poster_path, final ImageView imageView, String width){
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageRequest request = new ImageRequest( URL_POSTER + width +poster_path+"",
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

    public static void getPopular(Context context, PopularObserver listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(URL_API_POPULAR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("results");

                            for(int i=0; i<10; i++){
                                JSONObject object= jsonArray.getJSONObject(i);

                                Film film = new Film(object.getInt("id"),
                                        object.getString("title"),
                                        object.getString("release_date"),
                                        object.getString("overview"),
                                        object.getInt("vote_average"),
                                        object.getString("original_language"),
                                        object.getString("poster_path")
                                );
                                listener.onReceivePopularInfo(film);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        queue.add(request);
    }
}