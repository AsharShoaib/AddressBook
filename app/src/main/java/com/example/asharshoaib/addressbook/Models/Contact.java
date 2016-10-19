package com.example.asharshoaib.addressbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by asharshoaib on 2016-10-14.
 */

public class Contact extends RealmObject implements Comparator<Contact>, Comparable<Contact>{
    @SerializedName("id")
    @Expose
    public Id id;

    @SerializedName("name")
    @Expose
    public Name name;

    @SerializedName("email")
    @Expose
    public String emails;

    @SerializedName("cell")
    @Expose
    @PrimaryKey
    public String numbers;

    @SerializedName("picture")
    @Expose
    private Picture picture;

    private String sortingName;

    public Contact() {
    }
    public Contact(Id id, Name name) {
        this.id = id;
        this.name = name;
        sortingName = this.name.getLast();
    }
    public Contact(String firstname, String lastname, String emailaddress, String phonenumber) {
        this.id = new Id(Id.getRandomString(5));
        this.name = new Name(firstname, lastname);
        this.emails = emailaddress;
        this.numbers = phonenumber;
        sortingName = this.name.getLast();
    }
    public Contact(Id id, Name name, String emails, String numbers, Picture picture) {
        this.id = id;
        this.name = name;
        this.emails = emails;
        this.numbers = numbers;
        this.picture = picture;
        sortingName = this.name.getLast();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name=" + name +
                ", emails='" + emails + '\'' +
                ", numbers='" + numbers + '\'' +
                ", picture=" + picture +
                '}';
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
        sortingName = this.name.getLast();
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getSortingName() {
        return sortingName;
    }

    public void setSortingName(String sortingName) {
        this.sortingName = sortingName;
    }

    @Override
    public int compareTo(Contact o) {
        return (this.name.getLast()).compareTo(o.getName().getLast());
    }

    @Override
    public int compare(Contact o1, Contact o2) {
        return (o1.name.getLast()).compareTo(o2.getName().getLast());
    }
}
