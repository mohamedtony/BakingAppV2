package com.example.android.bakingapp.utilities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by medo on 27-Oct-17.
 */

public class BakingResponse implements Parcelable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("ingredients")
        @Expose
        private ArrayList<BakingIngredient> ingredients = null;
        @SerializedName("steps")
        @Expose
        private ArrayList<BakingSteps> steps = null;
        @SerializedName("servings")
        @Expose
        private int servings;
        @SerializedName("image")
        @Expose
        private String image;


        protected BakingResponse(Parcel in) {
                name = in.readString();
                ingredients = in.createTypedArrayList(BakingIngredient.CREATOR);
                steps = in.createTypedArrayList(BakingSteps.CREATOR);
                servings = in.readInt();
                image = in.readString();
        }

        public static final Creator<BakingResponse> CREATOR = new Creator<BakingResponse>() {
                @Override
                public BakingResponse createFromParcel(Parcel in) {
                        return new BakingResponse(in);
                }

                @Override
                public BakingResponse[] newArray(int size) {
                        return new BakingResponse[size];
                }
        };

        public int getId() {
            return id;
        }



        public String getName() {
            return name;
        }


        public ArrayList<BakingIngredient> getIngredients() {
            return ingredients;
        }



        public ArrayList<BakingSteps> getSteps() {
            return steps;
        }


        public Integer getServings() {
            return servings;
        }


        public String getImage() {
            return image;
        }


        @Override
        public int describeContents() {
                return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(name);
                parcel.writeTypedList(ingredients);
                parcel.writeTypedList(steps);
                parcel.writeInt(servings);
                parcel.writeString(image);
        }
}
