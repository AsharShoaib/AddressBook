package com.example.asharshoaib.addressbook;

import android.support.v7.util.DiffUtil;

import com.example.asharshoaib.addressbook.Models.Contact;

import java.util.List;

/**
 * Created by asharshoaib on 2016-10-17.
 */

public class ContactDiffUtill extends DiffUtil.Callback {

    private List<Contact> mOldList;
    private List<Contact> mNewList;

    public ContactDiffUtill(List<Contact> mOldList, List<Contact> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getId() == mNewList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Contact oldContact = mOldList.get(oldItemPosition);
        Contact newContact = mNewList.get(newItemPosition);

        if (oldContact.getName() == newContact.getName() && oldContact.getNumber() == newContact.getNumber()) {
            return true;
        }
        return false;
    }
}
