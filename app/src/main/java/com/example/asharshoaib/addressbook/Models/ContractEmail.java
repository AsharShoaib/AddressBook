package com.example.asharshoaib.addressbook.Models;

/**
 * Created by asharshoaib on 2016-10-14.
 */
public class ContractEmail extends ContactEmail {

    public String emailAddress;
    public String type;

    public ContractEmail(String emailAddress, String type) {
        this.emailAddress = emailAddress;
        this.type = type;
    }
}
