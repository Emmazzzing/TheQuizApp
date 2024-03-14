package edu.northeastern.thequizapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private String base_url = "http://10.0.2.2/quiz/";

    public Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
    }
}
