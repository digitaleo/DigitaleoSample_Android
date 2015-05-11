package com.digitaleo.sampledigitaleosdk.contact;

import com.digitaleo.http.com.google.api.client.util.Key;

/**
 * Created by flemontreer on 23/04/15.
 */
public class Contact extends com.digitaleo.sdk.push.model.contacts.EOContact {

    @Key(LASTNAME)
    private String nom;

    @Key(FIRSTNAME)
    private String prenom;

    @Key(MOBILE)
    private String mobile;

    @Key(EMAIL)
    private String email;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
