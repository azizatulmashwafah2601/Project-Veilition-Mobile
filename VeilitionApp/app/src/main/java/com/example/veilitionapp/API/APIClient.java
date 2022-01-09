package com.example.veilitionapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String BASE_URL = "https://ws-tif.com/veilition/Retrofit/";
    private static APIClient apiClient;
    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if (retrofit == null){
            retrofit = new  Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static synchronized APIClient getInstance(){

        if(apiClient==null){
            apiClient=new APIClient();
        }
        return apiClient;
    }

    public APIInterface getApi(){
        return retrofit.create(APIInterface.class);
    }
}

