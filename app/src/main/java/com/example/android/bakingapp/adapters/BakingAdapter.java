package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activity.ReceipeDetailActivity;
import com.example.android.bakingapp.utilities.BakingResponse;

import java.util.List;

/**
 * Created by medo on 03-Sep-17.
 */

public class BakingAdapter extends RecyclerView.Adapter<BakingAdapter.BakingViewHolder> {


    private Context mContex;
    private List<BakingResponse> bakingResponses;

    public BakingAdapter(Context mContex, List<BakingResponse> bakingResponses) {
        this.mContex = mContex;
        this.bakingResponses=bakingResponses;
    }


    @Override
    public BakingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipes_item, parent, false);
        return new BakingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BakingViewHolder holder, int position) {

        holder.recipe_tv.setText(bakingResponses.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return bakingResponses.size();
    }

    public interface OnItemClicked {
        public void onItemClickedLisener(int id);
    }

    public class BakingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView recipe_tv;

        public BakingViewHolder(View itemView) {
            super(itemView);


            recipe_tv = (TextView) itemView.findViewById(R.id.tv_recipe);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int pos = getAdapterPosition();
          //  Intent intent=new Intent(mContex,ReceipeDetailActivity.class);

            Intent intent=new Intent(mContex,ReceipeDetailActivity.class);
            intent.putExtra("Baking",(Parcelable) bakingResponses.get(pos));

            mContex.startActivity(intent);
        }

    }

}
