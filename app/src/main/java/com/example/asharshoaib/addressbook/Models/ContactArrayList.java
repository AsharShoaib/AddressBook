package com.example.asharshoaib.addressbook.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by asharshoaib on 2016-10-17.
 */
public class ContactArrayList extends RealmObject{
    @SerializedName("results")
    public RealmList<Contact> contactList;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Contact c : contactList)
        {
            sb.append(c);
            sb.append("\n");
        }

        return "ContactArrayList{" +
                "contactList=" + contactList +
                '}';
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(RealmList<Contact> contactList) {
        this.contactList = contactList;
    }

    public ContactArrayList(RealmList<Contact> contactList) {

        this.contactList = contactList;
    }
    public ContactArrayList() {

    }
}
