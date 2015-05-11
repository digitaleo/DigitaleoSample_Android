package com.digitaleo.sampledigitaleosdk.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.digitaleo.sampledigitaleosdk.R;
import com.digitaleo.sdk.push.model.messages.EOMessage;
import com.digitaleo.sdk.push.model.messages.EOMessageStatus;

import java.util.List;

public class MessageAdapter extends BaseAdapter {

    private final LayoutInflater inflater;

    private final List<EOMessage> lstMessage;

    public MessageAdapter(Context context, List<EOMessage> lstMessage) {
        this.lstMessage = lstMessage;
        this.inflater = LayoutInflater.from(context);
    }

    public void replaceAll(List<EOMessage> pLstMessage) {
        lstMessage.clear();
        lstMessage.addAll(pLstMessage);
    }

    @Override
    public int getCount() {
        return lstMessage.size();
    }

    @Override
    public Object getItem(int position) {
        return lstMessage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layoutItem;

        if (convertView == null) {
            layoutItem = (RelativeLayout) inflater.inflate(R.layout.message, parent, false);
        } else {
            layoutItem = (RelativeLayout) convertView;
        }

        TextView text = (TextView) layoutItem.findViewById(R.id.message_text);
        text.setText(lstMessage.get(position).getText());

        TextView date = (TextView) layoutItem.findViewById(R.id.message_date);
        date.setText(lstMessage.get(position).getDateExpiration());

        ImageView status = (ImageView) layoutItem.findViewById(R.id.message_status);
        if (lstMessage.get(position).getStatus() == EOMessageStatus.OPENED) {
            layoutItem.setBackgroundColor(layoutItem.getResources().getColor(R.color.blue));
            status.setImageResource(R.drawable.ic_action_read);
        } else {
            layoutItem.setBackgroundColor(layoutItem.getResources().getColor(R.color.white));
            status.setImageResource(R.drawable.ic_action_unread);
        }

        return layoutItem;
    }
}
