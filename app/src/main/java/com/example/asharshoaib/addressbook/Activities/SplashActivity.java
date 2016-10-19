package com.example.asharshoaib.addressbook.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.asharshoaib.addressbook.Models.Contact;
import com.example.asharshoaib.addressbook.Models.ContactArrayList;
import com.example.asharshoaib.addressbook.OkHttpApiCall;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import io.realm.Realm;
import okhttp3.OkHttpClient;

/**
 * Created by asharshoaib on 2016-10-17.
 */

public class SplashActivity extends AppCompatActivity {

    public ArrayList<Contact> contacts;
    private OkHttpClient client;
    private String response;
    private ContactArrayList contactList;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = new OkHttpClient();
        loadContent();
    }

    private void loadContent() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                Realm realm = Realm.getDefaultInstance();
                try {
                    response = OkHttpApiCall.GET(client, OkHttpApiCall.RequestBuilder.buildURL());
                    Gson gson = new Gson();

                    //Need to confirm data parsing currently
                    contactList = gson.fromJson(response, ContactArrayList.class);
                    realm.beginTransaction();
                    for (Contact c : contactList.getContactList()) {
                        c.setSortingName(c.getName().getLast());

                        realm.insert(c);
                    }
                    realm.commitTransaction();
                    Log.d("Response", response);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    realm.close();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                done();
            }
        }.execute();
    }

    private void done() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
