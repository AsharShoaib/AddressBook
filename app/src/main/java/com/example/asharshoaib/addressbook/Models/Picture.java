package com.example.asharshoaib.addressbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by asharshoaib on 2016-10-17.
 */
public class Picture extends RealmObject {

    @SerializedName("large")
    @Expose
    private String large;

    @SerializedName("medium")
    @Expose
    private String medium;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @Override
    public String toString() {
        return "Picture{" +
                "large='" + large + '\'' +
                ", medium='" + medium + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }

    public String getLarge() {
        return large;
    }


    public void setLarge(String large) {
        this.large = large;
    }


    public String getMedium() {
        return medium;
    }


    public void setMedium(String medium) {
        this.medium = medium;
    }


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}