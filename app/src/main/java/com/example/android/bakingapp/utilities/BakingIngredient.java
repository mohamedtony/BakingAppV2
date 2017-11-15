package com.example.android.bakingapp.utilities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by medo on 27-Oct-17.
 */

public class BakingIngredient implements Parcelable{

    @SerializedName("quantity")
    @Expose
    private double quantity;
    @SerializedName("measure")
    @Expose
    private String measure;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    protected BakingIngredient(Parcel in) {
        quantity = in.readDouble();
        measure = in.readString();
        ingredient = in.readString();
    }

    public static final Creator<BakingIngredient> CREATOR = new Creator<BakingIngredient>() {
        @Override
        public BakingIngredient createFromParcel(Parcel in) {
            return new BakingIngredient(in);
        }

        @Override
        public BakingIngredient[] newArray(int size) {
            return new BakingIngredient[size];
        }
    };

    public double getQuantity() {
        return quantity;
    }


    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }
}
