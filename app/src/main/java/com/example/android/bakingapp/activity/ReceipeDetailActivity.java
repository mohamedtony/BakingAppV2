package com.example.android.bakingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.RecipeDetailAdapter;
import com.example.android.bakingapp.fragment.MasterDetailFragment;
import com.example.android.bakingapp.fragment.MasterStepFragment;
import com.example.android.bakingapp.utilities.BakingResponse;
import com.example.android.bakingapp.utilities.BakingSteps;

import java.util.ArrayList;

public class ReceipeDetailActivity extends AppCompatActivity implements RecipeDetailAdapter.OnItemClickListenr {


    private boolean mTowPane = false;
    private BakingResponse bakingResponse;
    private ArrayList<BakingSteps> bakingStepses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipe_detail);


        TextView textView = (TextView) findViewById(R.id.tv_recipe_detail);

        bakingResponse = (BakingResponse) getIntent().getParcelableExtra("Baking");
        bakingStepses = bakingResponse.getSteps();


        Bundle bundle = new Bundle();
        bundle.putParcelable("baking_me", bakingResponse);


        getSupportActionBar().setTitle(bakingResponse.getName());


        // set Fragmentclass Arguments
        MasterDetailFragment masterDetailFragment = new MasterDetailFragment();
        masterDetailFragment.setArguments(bundle);


        if(findViewById(R.id.master_fragment_container)!=null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.master_fragment_container, masterDetailFragment)
                    .commit();
        }


        if (findViewById(R.id.baking_detail_linear_layout) != null) {
            mTowPane = true;

            MasterStepFragment masterStepFragment = new MasterStepFragment();

            BakingSteps steps = bakingResponse.getSteps().get(0);

            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("baking_fragment_step", steps);

            masterStepFragment.setArguments(bundle2);


            getSupportFragmentManager().beginTransaction()
                    .add(R.id.head_container, masterStepFragment)
                    .commit();


        } else {

            mTowPane = false;

        }


    }

    @Override
    public void onImageClicked(int position) {
      //  Toast.makeText(this, " pos you clicked " + position, Toast.LENGTH_LONG).show();

        if (mTowPane) {
            MasterStepFragment masterStepFragment = new MasterStepFragment();

            BakingSteps steps = bakingResponse.getSteps().get(position);

            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("baking_fragment_step", steps);

            masterStepFragment.setArguments(bundle2);


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.head_container, masterStepFragment)
                    .commit();
        } else {
            Intent intent = new Intent(ReceipeDetailActivity.this, StepDetailActivity.class);

            intent.putParcelableArrayListExtra("baking_steps", bakingStepses);
            intent.putExtra("pos", position);
            intent.putExtra("name",bakingResponse.getName());
            startActivity(intent);
        }

    }
}
