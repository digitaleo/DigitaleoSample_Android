package com.digitaleo.sampledigitaleosdk.home;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitaleo.sampledigitaleosdk.R;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView version = (TextView) view.findViewById(R.id.home_sdk_version);
        version.setText("SDK Version : " + getResources().getText(com.digitaleo.sdk.R.string.SDK_VERSION));
    }
}
