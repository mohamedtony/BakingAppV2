package com.example.android.bakingapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.android.bakingapp.fragment.MasterStepFragment;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.utilities.BakingSteps;

import java.util.ArrayList;

public class StepDetailActivity extends AppCompatActivity {

    private ArrayList<BakingSteps> bakingStepses;
    private int pos;
    private int actual_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        bakingStepses = getIntent().getParcelableArrayListExtra("baking_steps");
        pos = getIntent().getIntExtra("pos", 0);
        String name=getIntent().getStringExtra("name");

        getSupportActionBar().setTitle(name);



        actual_pos = pos;

        MasterStepFragment masterStepFragment = new MasterStepFragment();

        BakingSteps steps = bakingStepses.get(pos);

        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("baking_fragment_step", steps);

        masterStepFragment.setArguments(bundle2);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.head_container, masterStepFragment)
                .commit();


    }

    public void next(View v) {


        if (actual_pos < bakingStepses.size() - 1) {
            actual_pos++;

            MasterStepFragment masterStepFragment = new MasterStepFragment();

            BakingSteps steps = bakingStepses.get(actual_pos);

            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("baking_fragment_step", steps);

            masterStepFragment.setArguments(bundle2);


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.head_container, masterStepFragment)
                    .commit();


        }


    }

    public void prev(View v) {

        if (actual_pos > 0) {

            actual_pos--;

            MasterStepFragment masterStepFragment = new MasterStepFragment();

            BakingSteps steps = bakingStepses.get(actual_pos);

            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("baking_fragment_step", steps);

            masterStepFragment.setArguments(bundle2);


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.head_container, masterStepFragment)
                    .commit();


        }


    }


}
