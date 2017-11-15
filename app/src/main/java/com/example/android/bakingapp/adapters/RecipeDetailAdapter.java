package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.utilities.BakingSteps;

import java.util.ArrayList;

/**
 * Created by medo on 27-Oct-17.
 */

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.RecepeViewHolder> {


    private Context mContex;
    private ArrayList<BakingSteps> stepsList;
    private OnItemClickListenr onItemClickListenr;


    public RecipeDetailAdapter(Context mContex, ArrayList<BakingSteps> stepsList) {
        this.mContex = mContex;
        this.stepsList = stepsList;
        onItemClickListenr = (OnItemClickListenr) mContex;

    }


    @Override
    public RecepeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_detail_item, parent, false);
        return new RecepeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecepeViewHolder holder, int position) {


        int pos = position + 1;
        holder.recipe_tv.setText(pos + ". " + stepsList.get(position).getShortDescription());


    }

    @Override
    public int getItemCount() {
        return stepsList.size();
    }

    public interface OnItemClickListenr {
        public void onImageClicked(int position);
    }

    public class RecepeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView recipe_tv;

        public RecepeViewHolder(View itemView) {
            super(itemView);


            recipe_tv = (TextView) itemView.findViewById(R.id.tv_recepe_detaile);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int pos = getAdapterPosition();
            onItemClickListenr.onImageClicked(pos);


/*        Intent intent=new Intent(mContex,StepDetailActivity.class);
        intent.putExtra("Step",stepsList.get(pos));
        intent.putExtra("pos",pos);
        intent.putParcelableArrayListExtra("steps",stepsList);

        mContex.startActivity(intent);*/
        }

    }

}
