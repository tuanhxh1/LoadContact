package com.example.tuan.loadcontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<PersonContact> {

    private Context mContext;
    private int mResource;
    private List<PersonContact> listContact;

    public ContactAdapter(Context context, int resource, List<PersonContact> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        listContact = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textName = convertView.findViewById(R.id.text_name);
            viewHolder.textPhone = convertView.findViewById(R.id.text_phoneNumber);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PersonContact personContact = listContact.get(position);
        viewHolder.textName.setText(personContact.getmName());
        viewHolder.textPhone.setText(personContact.getmPhone());
        return convertView;
    }

    private class ViewHolder {
        private TextView textName;
        private TextView textPhone;
    }
}
