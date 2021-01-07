package com.MJ.Hack.Sehat;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class News {
    private static Retrofit postService=null;
    private static final String BASE_URL = "http://newsapi.org/v2/";
    private static Retrofit retrofit;

    public interface GetDataService{

        @GET("top-headlines?country=in&category=health&apiKey=a85024f1e84f4afebb0ec2253f3829ae")
        Call<Postlist> getPostlist();

    }


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }



}
