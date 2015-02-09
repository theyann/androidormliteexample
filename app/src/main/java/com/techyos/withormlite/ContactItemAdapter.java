package com.techyos.withormlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactItemAdapter extends ArrayAdapter<Contact> {

    /**
     * Static
     */

    private static int LAYOUT_ID = R.layout.contact_list_item;

    /**
     * Attributes
     */

    private LayoutInflater inflater;

    /**
     * Constructors
     */

    public ContactItemAdapter(Context context, List<Contact> objects) {
        super(context, LAYOUT_ID, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Overridden Methods: ArrayAdapter
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null || convertView.getTag() == null) {
            convertView = inflater.inflate(LAYOUT_ID, parent, false);

            holder = new ViewHolder();
            holder.viewId = (TextView) convertView.findViewById(R.id.contactId);
            holder.viewFirstName = (TextView) convertView.findViewById(R.id.contactFirstName);
            holder.viewLastName = (TextView) convertView.findViewById(R.id.contactLastName);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contact contact = getItem(position);
        holder.viewId.setText(String.valueOf(contact.getId()));
        holder.viewFirstName.setText(contact.getFirstName());
        holder.viewLastName.setText(contact.getLastName());

        return convertView;
    }

    /**
     * Inner Classes
     */

    private class ViewHolder {
        TextView viewId;
        TextView viewFirstName;
        TextView viewLastName;
    }
}
