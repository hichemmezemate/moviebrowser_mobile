    package com.example.moviebrowser.models;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.moviebrowser.R;
    import com.example.moviebrowser.services.Api;

    import java.util.ArrayList;

    public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
        private ArrayList<Film> films;
        private Context context;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textViewName;
            public ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                //textViewName = itemView.findViewById(R.id.popFilmText);
                imageView = itemView.findViewById(R.id.popFilmImage);
            }
        }

        public PopularAdapter(ArrayList<Film> films, Context context) {
            this.films = films;
            this.context = context;
        }

        public void setFilms(ArrayList<Film> films) {
            this.films = films;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film_card, parent, false);


            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Film film = films.get(position);
            //holder.textViewName.setText(film.getTitle());



            Api.loadFilmPoster(context, film.getPoster_path(), holder.imageView, "500");

        }

        @Override
        public int getItemCount() {
            return films.size();
        }

        @Override
        public long getItemId(int position) {
            return films.get(position).getId();
        }
    }
