package com.example.asharshoaib.addressbook.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.asharshoaib.addressbook.ContactRecyclerViewAdapter;
import com.example.asharshoaib.addressbook.Models.Contact;
import com.example.asharshoaib.addressbook.Models.ContactArrayList;
import com.example.asharshoaib.addressbook.OkHttpApiCall;
import com.example.asharshoaib.addressbook.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity {

    public ArrayList<Contact> contacts;
    private OkHttpClient client;
    private String response;
    private ContactArrayList contactList;
    private Realm realm;
    private RealmRecyclerView realmRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Need to add a splash screen activity to do initial download
        //After initial launch downloads will be done in background (possible syncing indication)
        // and updated in real-time
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Contact> contactsResult = realm.where(Contact.class).findAll();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        realmRecyclerView = (RealmRecyclerView) findViewById(R.id.realm_recycler_view);
        ContactRecyclerViewAdapter adapter = new ContactRecyclerViewAdapter(contactsResult, this, true, true);
        realmRecyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}