package com.example.android.bakingapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.BakingAdapter;
import com.example.android.bakingapp.utilities.ApiRetrofitClient;
import com.example.android.bakingapp.utilities.BakingResponse;
import com.example.android.bakingapp.utilities.RequestBakingInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    CountingIdlingResource idlingResource=new CountingIdlingResource("Data_loader");

    private BakingAdapter bakingAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.reycler_recepies);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);


        idlingResource.increment();


        final RequestBakingInterface requestMovieInterface = ApiRetrofitClient.getApiRetrofitClient().create(RequestBakingInterface.class);
        Call<List<BakingResponse>> call = requestMovieInterface.getBakingList();

        call.enqueue(new Callback<List<BakingResponse>>() {
            @Override
            public void onResponse(Call<List<BakingResponse>> call, Response<List<BakingResponse>> response) {

                List<BakingResponse> bakingResponse=response.body();
               // Log.d("Baking",bakingResponse.get(0).getName());
              //  Toast.makeText(MainActivity.this, " name "+bakingResponse.get(0).getName(), Toast.LENGTH_LONG).show();
                bakingAdapter=new BakingAdapter(MainActivity.this,bakingResponse);
                mRecyclerView.setAdapter(bakingAdapter);

/*                mRecyclerView.getLayoutManager().scrollToPosition(3);
                mRecyclerView.getAdapter().notifyDataSetChanged();*/

                idlingResource.decrement();
            }

            @Override
            public void onFailure(Call<List<BakingResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this, " error "+t.getMessage(), Toast.LENGTH_LONG).show();
            Log.e( "error",t.getMessage());
            }
        });

    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {

        return idlingResource;
    }

}
