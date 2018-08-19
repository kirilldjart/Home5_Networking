package com.drob.kirill.home5_networking;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.drob.kirill.home5_networking.data.repository.MovieResponse;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //...см Лекцию 3
    private MovieRecyclerAdapter adapter;
    int column;
    GridLayoutManager lLayout;

    public static final String CLIENT_SECRET = "2aaf22ab";


    private final MovieRecyclerAdapter.OnItemClickListener clickListener = position -> {
        MovieResponse.Search movie = adapter.getItem(position);
        //TO DO smth in future:)
      //  String clickMessage = getString(R.string.actor_click_action, movie.title);
         };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }


        // GET ORientation in RUNTIME instead of resources/values-land /columns=4
         column=2;
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE))
                .getDefaultDisplay();
        int orientation = display.getRotation();
        if (orientation == Surface.ROTATION_90
                || orientation == Surface.ROTATION_270) {
            // logic for landscape mode here
            column=3; // for_lesson5.

        }
        RecyclerView list = findViewById(R.id.actorList);
        EditText edit_search = findViewById(R.id.edit_search);

        Button btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //запрос сетевой
                String searchString = edit_search.getText().toString();
                final Call<MovieResponse> call = App.omdbService.getMovies(searchString);
                //final Call<MovieResponse> call = App.omdbService.getMoviesWithApi(searchString,CLIENT_SECRET);
                call.enqueue(new Callback<MovieResponse>() {
                                 @Override
                                 public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                                     Log.d("academy","response"+response.body());
                                     if (response.body()==null) return;
                                     adapter = new MovieRecyclerAdapter(MainActivity.this, response.body().search, clickListener);
                                     list.setAdapter(adapter);
                                     lLayout=new GridLayoutManager(MainActivity.this,column);
                                     list.setLayoutManager(lLayout);
                                     list.addItemDecoration(new VerticalSpaceItemDecoration(2));
                                 }

                                 @Override
                                 public void onFailure(Call<MovieResponse> call, Throwable t) {
                                     Log.d("academy","error"+t);

                                 }
                             }

                );

            }
        });


        }






     public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
