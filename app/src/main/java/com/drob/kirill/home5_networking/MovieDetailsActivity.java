package com.drob.kirill.home5_networking;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MovieDetailsActivity extends AppCompatActivity {


// TO_DO   FOR FUTURE USE.....




    private static final String EXTRA_FULL_NAME="name";
    private static final String EXTRA_PHOTO = "photo";
    private static final String EXTRA_WIKI_ARTICLE = "wiki_article";
/*
    public static void start(Context context,Actor actor){
        context.startActivity(
                new Intent(context,MovieDetailsActivity.class)
                .putExtra(EXTRA_FULL_NAME,actor.getName())
                .putExtra(EXTRA_PHOTO, actor.getAvatar())
                .putExtra(EXTRA_WIKI_ARTICLE, actor.getWikiArticle())
        );


    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_details);

        Intent intent = getIntent();
        String fullName = intent.getStringExtra(EXTRA_FULL_NAME);
        String wikiArticle = intent.getStringExtra(EXTRA_WIKI_ARTICLE);
        Uri avatar = intent.getParcelableExtra(EXTRA_PHOTO);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(fullName);
        }
/*
        ImageView photoView = findViewById(R.id.photoView);
        TextView wikiInfoView = findViewById(R.id.wikiInfoView);

        Glide.with(this).load(avatar).apply(new RequestOptions()
                .placeholder(R.drawable.avatar_default_details)
                .fallback(R.drawable.avatar_default_details)
                .centerCrop()).into(photoView);

        wikiInfoView.setText(wikiArticle);*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}


