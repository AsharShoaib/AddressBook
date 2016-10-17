package com.example.asharshoaib.addressbook.Models;

import java.util.List;

/**
 * Created by asharshoaib on 2016-10-17.
 */
public class ContactArrayList {
    public List<Contact> contactList;

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

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public ContactArrayList(List<Contact> contactList) {

        this.contactList = contactList;
    }
}
