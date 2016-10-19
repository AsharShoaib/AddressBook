package com.example.asharshoaib.addressbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by asharshoaib on 2016-10-17.
 */
public class Name extends RealmObject {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("first")
    @Expose
    @Required
    private String first;

    @SerializedName("last")
    @Expose
    @Required
    private String last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }
    public Name() {
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getFirst() {
        return first;
    }


    public void setFirst(String first) {
        this.first = first;
    }


    public String getLast() {
        return last;
    }


    public void setLast(String last) {
        this.last = last;
    }


    @Override
    public String toString() {
        return "Name{" +
                "title='" + title + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }

}