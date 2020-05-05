package com.example.flexinfitness;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {
    String Name = null;
    String Desc = null;
    String Instructions = null;
    List<String> Ingredients;

    protected Recipe(Parcel in) {
        Name = in.readString();
        Desc = in.readString();
        Instructions = in.readString();
        if (in.readByte() == 0x01) {
            Ingredients = new ArrayList<String>();
            in.readList(Ingredients, String.class.getClassLoader());
        } else {
            Ingredients = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Desc);
        dest.writeString(Instructions);
        if (Ingredients == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(Ingredients);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}