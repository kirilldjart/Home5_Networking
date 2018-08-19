package com.drob.kirill.home5_networking.network;


import com.drob.kirill.home5_networking.MainActivity;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {

    public static final String BASE_URL ="http://www.omdbapi.com";

    private final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private final HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);


    private final Interceptor apiKeyInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request original = chain.request();
            final HttpUrl originalHttpUrl = original.url();

            final HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", MainActivity.CLIENT_SECRET)
                    .build();

            final Request.Builder requestBuilder = original.newBuilder()
                    .url(url);
            final Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    };



    public final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client( httpClient.addInterceptor(logging)
                                .addInterceptor(apiKeyInterceptor)
                                .build())
            .build();


    public OmdbService omdbService = retrofit.create(OmdbService.class);


}
