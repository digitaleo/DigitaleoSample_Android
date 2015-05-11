package com.digitaleo.sampledigitaleosdk.contact;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.digitaleo.sampledigitaleosdk.R;
import com.digitaleo.sdk.http.EOHTTPResponseListener;
import com.digitaleo.sdk.http.model.EOCount;
import com.digitaleo.sdk.http.model.EOResponseWrapper;
import com.digitaleo.sdk.push.service.EOSDKPush;

public class ContactFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText nom = (EditText) view.findViewById(R.id.contact_nom);
        final EditText prenom = (EditText) view.findViewById(R.id.contact_prenom);
        final EditText mobile = (EditText) view.findViewById(R.id.contact_mobile);
        final EditText email = (EditText) view.findViewById(R.id.contact_email);

        Contact contact = EOSDKPush.getContactService().getContact(Contact.class);
        if (contact != null) {
            nom.setText(contact.getNom());
            prenom.setText(contact.getPrenom());
            mobile.setText(contact.getMobile());
            email.setText(contact.getEmail());
        }

        Button save = (Button) view.findViewById(R.id.contact_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contact contact = new Contact();
                contact.setNom(nom.getText().toString());
                contact.setPrenom(prenom.getText().toString());
                contact.setMobile(mobile.getText().toString());
                contact.setEmail(email.getText().toString());

                EOSDKPush.getContactService().pushContact(getActivity(), contact, new EOHTTPResponseListener<EOCount>() {
                    @Override
                    public void onResponse(EOResponseWrapper<EOCount> response) {
                        Toast.makeText(getActivity(), "Contact successfully updated", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(EOResponseWrapper<com.digitaleo.sdk.http.model.error.Error> error) {
                        Toast.makeText(getActivity(), "Error on contact update", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
