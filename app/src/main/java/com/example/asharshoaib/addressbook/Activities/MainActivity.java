package com.example.asharshoaib.addressbook.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.asharshoaib.addressbook.ContactRecyclerViewAdapter;
import com.example.asharshoaib.addressbook.Models.Contact;
import com.example.asharshoaib.addressbook.Models.ContactArrayList;
import com.example.asharshoaib.addressbook.MultiInputMaterialDialogBuilder;
import com.example.asharshoaib.addressbook.OkHttpApiCall;
import com.example.asharshoaib.addressbook.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
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
        realm = Realm.getDefaultInstance();

        RealmResults<Contact> contactsResult = realm.where(Contact.class).findAll();
        contactsResult = contactsResult.sort("sortingName", Sort.ASCENDING);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewContact();
            }
        });

        realmRecyclerView = (RealmRecyclerView) findViewById(R.id.realm_recycler_view);
        ContactRecyclerViewAdapter adapter = new ContactRecyclerViewAdapter(contactsResult, this, true, true);
        realmRecyclerView.setAdapter(adapter);

    }

    private void addNewContact() {

        new MultiInputMaterialDialogBuilder(this)
                .addInput(InputType.TYPE_TEXT_VARIATION_PERSON_NAME, null, "First Name", new MultiInputMaterialDialogBuilder.InputValidator() {
                    @Override
                    public CharSequence validate(CharSequence input) {
                        if (input.length() <= 0) {
                            return "First Name cannot be empty";
                        }
                        return null;
                    }
                })
                .addInput(InputType.TYPE_TEXT_VARIATION_PERSON_NAME, null, "Last Name", new MultiInputMaterialDialogBuilder.InputValidator() {
                    @Override
                    public CharSequence validate(CharSequence input) {
                        if (input.length() <= 0) {
                            return "Last Name cannot be empty";
                        }
                        return null;
                    }
                })
                .addInput(InputType.TYPE_CLASS_PHONE, null, "Phone Number", new MultiInputMaterialDialogBuilder.InputValidator() {
                    @Override
                    public CharSequence validate(CharSequence input) {
                        if (input.length() > 0 && android.util.Patterns.PHONE.matcher(input).matches()) {
                            return null;
                        } else {
                            return "Enter a valid phone number";
                        }
                    }
                })
                .addInput(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, null, "Email", new MultiInputMaterialDialogBuilder.InputValidator() {
                    @Override
                    public CharSequence validate(CharSequence input) {
                        if (!TextUtils.isEmpty(input)) {
                            if (android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
                                return null;
                            } else {
                                return "Must be a valid Email Address";
                            }
                        }
                        return null;
                    }
                })
                .inputs(new MultiInputMaterialDialogBuilder.InputsCallback() {
                    @Override
                    public void onInputs(MaterialDialog dialog, List<CharSequence> inputs, boolean allInputsValidated) {
                        if (allInputsValidated) {
                            String firstname = inputs.get(0).toString();
                            String lastname = inputs.get(1).toString();
                            String emailaddress = inputs.get(3).toString();
                            String phonenumber = inputs.get(2).toString();
                            try {
                                Contact c = new Contact(firstname, lastname, emailaddress, phonenumber);

                                // Move realm operations outside of this call back and persist data
                                realm.beginTransaction();
                                realm.insert(c);
                                realm.commitTransaction();
                            } finally {
//                            realm.close();
                            }
                        }
                    }
                })
                .title(R.string.add_contact)
                .positiveText("Confirm")
                .negativeText("Cancel")
                .show();
    }

    public static void editNewContact(final Context c, final Contact contact) {

        new MultiInputMaterialDialogBuilder(c)
                .addInput(InputType.TYPE_TEXT_VARIATION_PERSON_NAME, contact.getName().getFirst(), "First Name", new MultiInputMaterialDialogBuilder.InputValidator() {
                    @Override
                    public CharSequence validate(CharSequence input) {
                        if (input.length() <= 0) {
                            return "First Name cannot be empty";
                        }
                        return null;
                    }
                })
                .addInput(InputType.TYPE_TEXT_VARIATION_PERSON_NAME, contact.getName().getLast(), "Last Name", new MultiInputMaterialDialogBuilder.InputValidator() {
                    @Override
                    public CharSequence validate(CharSequence input) {
                        if (input.length() <= 0) {
                            return "Last Name cannot be empty";
                        }
                        return null;
                    }
                })
                .addInput(InputType.TYPE_CLASS_PHONE, contact.getNumbers(), "Phone Number", new MultiInputMaterialDialogBuilder.InputValidator() {
                    @Override
                    public CharSequence validate(CharSequence input) {
                        if (input.length() > 0 && Patterns.PHONE.matcher(input).matches()) {
                            return null;
                        } else {
                            return "Enter a valid phone number";
                        }
                    }
                })
                .addInput(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, contact.getEmails(), "Email", new MultiInputMaterialDialogBuilder.InputValidator() {
                    @Override
                    public CharSequence validate(CharSequence input) {
//                        if (!TextUtils.isEmpty(input)) {
//                            if (Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
//                                return null;
//                            } else {
//                                return "Must be a valid Email Address";
//                            }
//                        }
                        return null;
                    }
                })
                .inputs(new MultiInputMaterialDialogBuilder.InputsCallback() {
                    public Realm realm;

                    @Override
                    public void onInputs(MaterialDialog dialog, List<CharSequence> inputs, boolean allInputsValidated) {
                        if (allInputsValidated) {
                            final String firstname = inputs.get(0).toString();
                            final String lastname = inputs.get(1).toString();
                            final String emailaddress = inputs.get(3).toString();
                            final String phonenumber = inputs.get(2).toString();
                            try {
                                realm = Realm.getDefaultInstance();
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        Contact temp = realm.where(Contact.class).equalTo("numbers", contact.getNumbers()).findFirst();
                                        temp.getName().setFirst(firstname);
                                        temp.getName().setLast(lastname);
                                        temp.setEmails(emailaddress);
                                        temp.setNumbers(phonenumber);
                                        realm.copyToRealmOrUpdate(temp);
                                    }
                                });
                            } finally {
                                realm.close();
                            }
                        }
                    }
                })
                .title(R.string.add_contact)
                .positiveText("Confirm")
                .negativeText("Cancel")
                .neutralText("Delete")
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Realm realm = null;
                        try {
                            realm = Realm.getDefaultInstance();
                            // Move realm operations outside of this call back and persist data
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    realm.where(Contact.class).equalTo("numbers", contact.getNumbers()).findAll().deleteAllFromRealm();
                                }
                            });
                        } finally {
                            realm.close();
                        }
                    }
                })
                .show();
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
