package com.example.android.bakingapp.utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by medo on 03-Sep-17.
 */

public class ApiRetrofitClient {

     public static final String BASE_URL="https://d17h27t6h515a5.cloudfront.net/";
     private static Retrofit retrofit=null;


     public static Retrofit getApiRetrofitClient(){

         if(retrofit==null){
             retrofit=new Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
         }
         return retrofit;

     }

}
