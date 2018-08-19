package com.drob.kirill.home5_networking;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import java.util.List;

import com.drob.kirill.home5_networking.data.repository.MovieResponse;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MovieRecyclerAdapter extends  RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>{
    @NonNull
    private final List<MovieResponse.Search> movies;
    @NonNull
    //private final Context context;
    private final LayoutInflater inflater;
    @Nullable
    private final OnItemClickListener clickListener;

   // private final RequestOptions imageOption;

    public MovieRecyclerAdapter(@NonNull Context context, @NonNull List<MovieResponse.Search> movies,
                                @Nullable OnItemClickListener clickListener) {
      //  this.context=context;
        this.movies = movies;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.clickListener = clickListener;
       /* this.imageOption = new RequestOptions()
                .placeholder(R.drawable.avatar_default_list)
                .fallback(R.drawable.avatar_default_list)
                .centerCrop();*/

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.list_item_actor_image, parent, false), clickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieResponse.Search movie = getItem(position);

        if (! TextUtils.isEmpty(movie.poster)){
            Picasso.get()
                    .load(movie.poster)
                    .into(holder.poster);

        }
        //   Glide.with(context).load(movie.poster).apply(imageOption).into(holder.poster);
//...см Лекцию 3




    }
    @Override
    public int getItemCount() {
        return movies.size();
    }


    public MovieResponse.Search getItem(int position) {
        return movies.get(position);
    }



    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    static class  ViewHolder extends RecyclerView.ViewHolder{
        final ImageView poster;

        public ViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
        this.poster=itemView.findViewById(R.id.avatar);

        itemView.setOnClickListener((view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            }));

        }

    }



}
