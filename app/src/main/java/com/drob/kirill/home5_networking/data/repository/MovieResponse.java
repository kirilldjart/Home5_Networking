package com.drob.kirill.home5_networking.data.repository;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


 public class MovieResponse {

    @SerializedName("Search")
    @Expose
    public List<Search> search = null;
    @SerializedName("totalResults")
    @Expose
    public String totalResults;
    @SerializedName("Response")
    @Expose
    public String response;





    public class Search {
        @SerializedName("Title")
        @Expose
        public String title;
        @SerializedName("Year")
        @Expose
        public String year;
        @SerializedName("imdbID")
        @Expose
        public String imdbID;
        @SerializedName("Type")
        @Expose
        public String type;
        @SerializedName("Poster")
        @Expose
        public String poster;
        }

 }