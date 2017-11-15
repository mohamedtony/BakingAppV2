package com.example.android.bakingapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.RecipeDetailAdapter;
import com.example.android.bakingapp.utilities.BakingIngredient;
import com.example.android.bakingapp.utilities.BakingResponse;
import com.example.android.bakingapp.myappwidget.UpdateBakingIntentService;

import java.util.ArrayList;

/**
 * Created by medo on 28-Oct-17.
 */

public class MasterDetailFragment extends Fragment {
    public MasterDetailFragment() {

    }

    private RecipeDetailAdapter recipeDetailAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.master_detail_item, container, false);

        Bundle b = getArguments();
        BakingResponse bakingResponse = (BakingResponse) b.getParcelable("baking_me");


        if (bakingResponse != null) {
           // Toast.makeText(getContext(), "from fragment name = " + bakingResponse.getName(), Toast.LENGTH_LONG).show();
        }



        //  getContext().getSupportActionBar().setTitle(bakingResponse.getName());
        if (bakingResponse != null) {
            ArrayList<BakingIngredient> bakingIngredient = bakingResponse.getIngredients();
            StringBuilder stringBuilder = new StringBuilder();
            ArrayList<String> ingredients = new ArrayList<>();

/*            for (BakingIngredient ingredient : bakingIngredient) {
                stringBuilder.append(ingredient.getIngredient() + " " + ingredient.getQuantity() + " " + ingredient.getMeasure() + "\n");
                ingredients.add(ingredient.getIngredient() + "\n" +
                        "Quantity: " + ingredient.getQuantity() + "\n" +
                        "Measure: " + ingredient.getMeasure() + "\n");
            }*/

            for (BakingIngredient ingredient : bakingIngredient) {
                stringBuilder.append(ingredient.getIngredient() + " " + ingredient.getQuantity() + " " + ingredient.getMeasure() + "\n");
                ingredients.add(ingredient.getIngredient()  + " " + ingredient.getQuantity() + " " + ingredient.getMeasure() );
            }
            TextView textView = (TextView) rootView.findViewById(R.id.tv_recipe_detail);
            textView.setText(stringBuilder);

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recipes_detail_recycler);
            layoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(layoutManager);

            recipeDetailAdapter = new RecipeDetailAdapter(getContext(), bakingResponse.getSteps());
            mRecyclerView.setAdapter(recipeDetailAdapter);


            //update widget
            UpdateBakingIntentService.startBakingService(getContext(), ingredients,bakingResponse.getName());
        }


        return rootView;
    }

/*    @Override
    public void onImageClicked(int position) {
        Toast.makeText(getContext(), " you clicked : "+position, Toast.LENGTH_LONG).show();
    }*/




 /*   @Override
    public void itemFragment(BakingResponse bakingResponse) {
                this.bakingResponse=bakingResponse;
    }*/
}
