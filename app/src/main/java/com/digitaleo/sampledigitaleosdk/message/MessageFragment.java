package com.digitaleo.sampledigitaleosdk.message;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.digitaleo.sampledigitaleosdk.R;
import com.digitaleo.sdk.http.EOHTTPResponseListener;
import com.digitaleo.sdk.http.model.EOCount;
import com.digitaleo.sdk.http.model.EOResponseWrapper;
import com.digitaleo.sdk.http.model.error.Error;
import com.digitaleo.sdk.push.model.messages.EOMessage;
import com.digitaleo.sdk.push.model.messages.EOMessageList;
import com.digitaleo.sdk.push.model.messages.EOMessageStatus;
import com.digitaleo.sdk.push.service.EOSDKPush;

public class MessageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ListView listView = (ListView) view.findViewById(R.id.message_listView);

        EOSDKPush.getMessageService().getMessages(getActivity(), null, new EOHTTPResponseListener<EOMessageList>() {
            @Override
            public void onResponse(EOResponseWrapper<EOMessageList> response) {
                listView.setAdapter(new MessageAdapter(getActivity(), response.getEntity().getLstEntity()));
            }

            @Override
            public void onError(EOResponseWrapper<Error> error) {
                Toast.makeText(getActivity(), "Erreur à la récupération des messages " + error.getEntity().getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EOMessage message = (EOMessage) listView.getAdapter().getItem(position);
                if (message.getStatus() != null) {
                    switch (message.getStatus()) {
                        case OPENED:
                            message.setStatus(EOMessageStatus.OK);
                            break;
                        case ON:
                        case OK:
                            message.setStatus(EOMessageStatus.OPENED);
                            break;
                    }

                    EOSDKPush.getMessageService().updateStatus(getActivity(), message, new EOHTTPResponseListener<EOCount>() {
                        @Override
                        public void onResponse(EOResponseWrapper<EOCount> eoCountEOResponseWrapper) {
                            EOSDKPush.getMessageService().getMessages(getActivity(), null, new EOHTTPResponseListener<EOMessageList>() {
                                @Override
                                public void onResponse(EOResponseWrapper<EOMessageList> response) {
                                    ((MessageAdapter) listView.getAdapter()).replaceAll(response.getEntity().getLstEntity());
                                    ((MessageAdapter) listView.getAdapter()).notifyDataSetChanged();
                                }

                                @Override
                                public void onError(EOResponseWrapper<Error> error) {
                                    Toast.makeText(getActivity(), "Erreur à la récupération des messages " + error.getEntity().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        @Override
                        public void onError(EOResponseWrapper<Error> errorEOResponseWrapper) {

                        }
                    });
                }
            }
        });
    }
}
