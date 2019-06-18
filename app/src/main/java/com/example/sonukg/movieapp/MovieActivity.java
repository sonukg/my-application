package com.example.sonukg.movieapp;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieActivity extends AppCompatActivity {

    private ImageView movieImage;
    private TextView movieTitle, movieSynopsis;
    private RatingBar movieRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);




            //setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//


        movieImage = (ImageView) findViewById(R.id.ivMovieLarge);
        movieTitle = (TextView) findViewById(R.id.tvMovieTitle);
        movieSynopsis = (TextView) findViewById(R.id.tvPlotsynopsis);
        movieRating = (RatingBar) findViewById(R.id.tvMovieRating);


        Bundle bundle = getIntent().getExtras();

        if (bundle!=null) {

            String title;
            String synopsis;
            String rating;
            String img;
            title=bundle.getString("title");
            synopsis=bundle.getString("synopsis");
            rating=bundle.getString("rating");
            img=bundle.getString("img");

            Glide.with(this)
                    .load(img)
                    .into(movieImage);

         getSupportActionBar().setTitle(title);
            String rat=rating;
            movieTitle.setText(title);
            movieSynopsis.setText(synopsis);
            movieRating.setRating(Float.parseFloat(rating));
        }

    }


}
