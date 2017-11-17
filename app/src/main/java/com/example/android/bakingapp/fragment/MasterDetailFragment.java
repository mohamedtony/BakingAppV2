package com.example.android.bakingapp.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.RecipeDetailAdapter;
import com.example.android.bakingapp.myappwidget.UpdateBakingIntentService;
import com.example.android.bakingapp.utilities.BakingIngredient;
import com.example.android.bakingapp.utilities.BakingResponse;

import java.util.ArrayList;

/**
 * Created by medo on 28-Oct-17.
 */

public class MasterDetailFragment extends Fragment {
    public MasterDetailFragment() {
       // mBundleRecyclerViewState=null;
    }

    private ArrayList<BakingIngredient> bakingIngredient;

    private RecipeDetailAdapter recipeDetailAdapter;
    private RecyclerView mRecyclerView;
   // private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager layoutManager;
    public String LIST_STATE_KEY="my_list";
    public Parcelable mList;
    private int mPositionIng = RecyclerView.NO_POSITION;

    private String KEY_POSITION_ING = "KeyPositionIng";
    private static final String BUNDLE_RECYCLER_LAYOUT = "m.recycler.layout";
    private final String KEY_RECYCLER_STATE = "recycler_state";

    private static Bundle mBundleRecyclerViewState=null;
   public Parcelable listState;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.master_detail_item, container, false);

        Bundle b = getArguments();
        BakingResponse bakingResponse = (BakingResponse) b.getParcelable("baking_me");
        int i=(Integer)b.getInt("isCreated");

       if (bakingResponse != null) {


             bakingIngredient = bakingResponse.getIngredients();
            StringBuilder stringBuilder = new StringBuilder();
            ArrayList<String> ingredients = new ArrayList<>();

            for (BakingIngredient ingredient : bakingIngredient) {
                stringBuilder.append(ingredient.getIngredient() + " " + ingredient.getQuantity() + " " + ingredient.getMeasure() + "\n");
                ingredients.add(ingredient.getIngredient()  + " " + ingredient.getQuantity() + " " + ingredient.getMeasure() );
            }
            TextView textView = (TextView) rootView.findViewById(R.id.tv_recipe_detail);
            textView.setText(stringBuilder);

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recipes_detail_recycler);
            layoutManager = new LinearLayoutManager(getContext());
       // layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(layoutManager);

            recipeDetailAdapter = new RecipeDetailAdapter(getContext(), bakingResponse.getSteps());
            mRecyclerView.setAdapter(recipeDetailAdapter);

            UpdateBakingIntentService.startBakingService(getContext(), ingredients,bakingResponse.getName());
        }


        return rootView;
    }


        @Override
    public void onResume()
    {
        super.onResume();

        // restore RecyclerView state
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(listState);

            mBundleRecyclerViewState=null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);

    }


/*    @Override
    public void onStop() {
        super.onStop();

        mBundleRecyclerViewState=null;
    }*/


}
