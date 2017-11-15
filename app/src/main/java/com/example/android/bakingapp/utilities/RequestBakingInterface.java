package com.example.android.bakingapp.utilities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by medo on 03-Sep-17.
 */

public interface RequestBakingInterface {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<BakingResponse>> getBakingList();


}
