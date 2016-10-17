package com.example.asharshoaib.addressbook.Models;

import java.util.ArrayList;

/**
 * Created by asharshoaib on 2016-10-14.
 */

public class Contact {

    public String id;
    public String name;
    public ArrayList<ContactEmail> emails;
    public ArrayList<ContactPhone> numbers;

    public Contact(String id, String name) {
        this.id = id;
        this.name = name;
        this.emails = new ArrayList<ContactEmail>();
        this.numbers = new ArrayList<ContactPhone>();
    }

    public void addEmail(String address, String type){
        emails.add(new ContractEmail(address, type));
    }

    public void addNumber(String number, String type){
        numbers.add(new ContactPhone(number, type));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ContactEmail> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<ContactEmail> emails) {
        this.emails = emails;
    }

    public ArrayList<ContactPhone> getNumber() {
        return numbers;
    }

    public void setNumber(ArrayList<ContactPhone> number) {
        this.numbers = number;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", emails=" + emails +
                ", numbesr=" + numbers +
                '}';
    }
    private static int lastContactId = 0;
    public static ArrayList<Contact> createContactsList(int numContacts) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Contact("Person " + ++lastContactId, "test"));
        }

        return contacts;
    }
}
