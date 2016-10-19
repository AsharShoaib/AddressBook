package com.example.asharshoaib.addressbook;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asharshoaib.addressbook.Activities.MainActivity;
import com.example.asharshoaib.addressbook.Models.Contact;
import com.example.asharshoaib.addressbook.Models.Picture;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by asharshoaib on 2016-10-14.
 */

public class ContactRecyclerViewAdapter extends RealmBasedRecyclerViewAdapter<Contact, ContactRecyclerViewAdapter.ViewHolder> {


    public ContactRecyclerViewAdapter(RealmResults<Contact> mContacts, Context mContext, boolean automaticUpdate, boolean animateIdType) {
        super(mContext, mContacts, automaticUpdate, animateIdType);
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        View v = inflater.inflate(R.layout.item_contact_card, viewGroup, false);
        ViewHolder vh = new ViewHolder((LinearLayout) v);
        return vh;
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final Contact contact = realmResults.get(position);
        final Picture picture = contact.getPicture();
        TextView nametextView = viewHolder.nameTextView;
        nametextView.setText(contact.getName().getFirst() + " " + contact.getName().getLast());
        TextView numbertextView = viewHolder.numberTextView;
        numbertextView.setText(contact.getNumbers());
        TextView emailtextView = viewHolder.emailTextView;
        emailtextView.setText(contact.getEmails());
        if (picture != null) {
            Glide.with(getContext())
                    .load(picture.getMedium())
                    .override(100,100)
                    .into(viewHolder.image);
        } else {
            viewHolder.image.setImageResource(R.drawable.placeholder);
        }
    }


    public class ViewHolder extends RealmViewHolder implements View.OnClickListener {

        private final TextView nameTextView;
        private final ImageView image;
        private final TextView numberTextView;
        private final TextView emailTextView;
        private final CardView cv;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cv = (CardView)itemView.findViewById(R.id.cv);
            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            numberTextView = (TextView) itemView.findViewById(R.id.contact_number);
            emailTextView = (TextView) itemView.findViewById(R.id.contact_email);
            this.image = (ImageView) itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View v) {
            MainActivity.editNewContact(v.getContext(), realmResults.get(getLayoutPosition()));
        }
    }

}
