package com.example.asharshoaib.addressbook;

import android.app.Application;

import com.example.asharshoaib.addressbook.Models.ContactModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by asharshoaib on 2016-10-17.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .modules(Realm.getDefaultModule(), new ContactModule())
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
