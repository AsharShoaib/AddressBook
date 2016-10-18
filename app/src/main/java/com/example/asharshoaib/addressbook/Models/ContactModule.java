package com.example.asharshoaib.addressbook.Models;

import io.realm.annotations.RealmModule;

/**
 * Created by asharshoaib on 2016-10-18.
 */

@RealmModule(classes = { Contact.class, Id.class, Name.class, Picture.class })
public class ContactModule {
}
